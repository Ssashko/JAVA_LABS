package lab6;
import java.sql.*;

public class DBService {
    final String URL;
    final String USER;
    final String PASSWORD;

    public DBService(String url, String user, String password) {
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    public void deleteTables() throws SQLException {

        final String[] drops_table = {
                "DROP TABLE Motherboard CASCADE;",
                "DROP TABLE Ssd CASCADE;",
                "DROP TABLE FormFactorSsd CASCADE;",
                "DROP TABLE MemoryTypeSsd CASCADE;",
                "DROP TABLE Hdd CASCADE;",
                "DROP TABLE FormFactorHdd CASCADE;",
                "DROP TABLE PCInterfaces CASCADE;",
                "DROP TABLE RAM CASCADE;",
                "DROP TABLE RamFormFactor CASCADE;",
                "DROP TABLE RamType CASCADE;",
                "DROP TABLE CPU CASCADE;",
                "DROP TABLE soketType CASCADE;"
        };
        try(Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            for (String sql : drops_table)
                statement.execute(sql);
        }
     }
    public void createTables() throws SQLException {
        final String[] create_cpu_table = {"""
                CREATE TABLE soketType(
                	_id SERIAL PRIMARY KEY NOT NULL,
                	_name VARCHAR(255) NOT NULL
                );
                """,
                """
                CREATE TABLE CPU(
                	_id SERIAL PRIMARY KEY NOT NULL,
                	vid INTEGER NOT NULL,
                	vendor VARCHAR(255) NOT NULL,
                	pid INTEGER NOT NULL,
                	_name VARCHAR(255) NOT NULL,
                	soketType_id INTEGER NOT NULL,
                	frequency BIGINT NOT NULL,
                	coreCount INTEGER NOT NULL,
                	hyperThreading BOOLEAN NOT NULL,
                	l1CacheCapacity INTEGER NOT NULL,
                	l2CacheCapacity INTEGER NOT NULL,
                	l3CacheCapacity INTEGER NOT NULL,
                	FOREIGN KEY (soketType_id) REFERENCES soketType(_id) ON DELETE SET NULL ON UPDATE CASCADE
                );
                """};
        final String[] create_ram_table = {"""
                CREATE TABLE RamType (
                	_id SERIAL PRIMARY KEY NOT NULL,
                	_name VARCHAR(255) NOT NULL
                );
                """,
                """
                CREATE TABLE RamFormFactor (
                 	_id SERIAL PRIMARY KEY NOT NULL,
                	_name VARCHAR(255) NOT NULL
                );""",
                """
                CREATE TABLE RAM (
                	_id SERIAL PRIMARY KEY NOT NULL,
                	vid INTEGER NOT NULL,
                	pid INTEGER NOT NULL,
                	vendor VARCHAR(255) NOT NULL,
                	capacity BIGINT NOT NULL,
                	_rank INTEGER NOT NULL,
                	frequency BIGINT NOT NULL,
                	_ramType INTEGER NOT NULL,
                	_ramFormFactor INTEGER NOT NULL,
                	
                	motherboard_id INTEGER NULL,
                	
                	FOREIGN KEY (motherboard_id) REFERENCES Motherboard(_id) ON DELETE SET NULL ON UPDATE CASCADE,
                	
                	FOREIGN KEY (_ramType) REFERENCES RamType(_id) ON DELETE SET NULL ON UPDATE CASCADE,
                	FOREIGN KEY (_ramFormFactor) REFERENCES RamFormFactor(_id) ON DELETE SET NULL ON UPDATE CASCADE
                );
                """};
        final String create_motherboard_table = """
                CREATE TABLE Motherboard(
                	_id SERIAL PRIMARY KEY NOT NULL,
                	vid INTEGER NOT NULL,
                	pid INTEGER NOT NULL,
                	vendor VARCHAR(255) NOT NULL,
                	cpu_id INTEGER NULL,
                	FOREIGN KEY (cpu_id) REFERENCES Cpu(_id) ON DELETE SET NULL ON UPDATE CASCADE
                );
                """;
        final String[] create_disk_table = {"""
                CREATE TABLE PCInterfaces (
                	_id SERIAL PRIMARY KEY NOT NULL,
                	_name VARCHAR(255) NOT NULL
                );""",
                """
                CREATE TABLE FormFactorHdd (
                	_id SERIAL PRIMARY KEY NOT NULL,
                	_name VARCHAR(255) NOT NULL
                );
                """,
                """
                CREATE TABLE Hdd
                (
                	_id SERIAL PRIMARY KEY NOT NULL,
                	vid INTEGER NOT NULL,
                	pid INTEGER NOT NULL,
                	vendor VARCHAR(255) NOT NULL,
                	capacity BIGINT NOT NULL,
                	interface_ INTEGER NOT NULL,
                	linearSpeedOfWrite BIGINT NOT NULL,
                	linearSpeedOfRead BIGINT NOT NULL,
                	randomSpeedOfWrite BIGINT NOT NULL,
                	randomSpeedOfRead BIGINT NOT NULL,
                	FOREIGN KEY (interface_) REFERENCES PCInterfaces(_id) ON DELETE SET NULL ON UPDATE CASCADE,
                	
                	_rotationSpeed INTEGER NOT NULL,
                	_formFactorHdd INTEGER NOT NULL,
                	FOREIGN KEY (_formFactorHdd) REFERENCES FormFactorHdd(_id) ON DELETE SET NULL ON UPDATE CASCADE,
                	
                	motherboard_id INTEGER NULL,
                	FOREIGN KEY (motherboard_id) REFERENCES Motherboard(_id) ON DELETE SET NULL ON UPDATE CASCADE

                );
                """,
                """
                CREATE TABLE MemoryTypeSsd (
                	_id SERIAL PRIMARY KEY NOT NULL,
                	_name VARCHAR(255) NOT NULL
                );
                """,
                """
                CREATE TABLE FormFactorSsd (
                	_id SERIAL PRIMARY KEY NOT NULL,
                	_name VARCHAR(255) NOT NULL
                );
                """,
                """
                CREATE TABLE Ssd
                (
                	_id SERIAL PRIMARY KEY NOT NULL,
                	vid INTEGER NOT NULL,
                	pid INTEGER NOT NULL,
                	vendor VARCHAR(255) NOT NULL,
                	capacity BIGINT NOT NULL,
                	interface_ INTEGER NOT NULL,
                	linearSpeedOfWrite BIGINT NOT NULL,
                	linearSpeedOfRead BIGINT NOT NULL,
                	randomSpeedOfWrite BIGINT NOT NULL,
                	randomSpeedOfRead BIGINT NOT NULL,
                	FOREIGN KEY (interface_) REFERENCES PCInterfaces(_id) ON DELETE SET NULL ON UPDATE CASCADE,
                	
                	_memoryTypeSsd INTEGER NOT NULL,
                	_formFactorSsd INTEGER NOT NULL,
                	FOREIGN KEY (_memoryTypeSsd) REFERENCES memoryTypeSsd(_id) ON DELETE SET NULL ON UPDATE CASCADE,
                	FOREIGN KEY (_formFactorSsd) REFERENCES formFactorSsd(_id) ON DELETE SET NULL ON UPDATE CASCADE,
                	
                	motherboard_id INTEGER NULL,
                	FOREIGN KEY (motherboard_id) REFERENCES Motherboard(_id) ON DELETE SET NULL ON UPDATE CASCADE
                );
                """};

        try(Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            for(String sql_command : create_cpu_table)
                statement.addBatch(sql_command);
            statement.addBatch(create_motherboard_table);
            for(String sql_command : create_ram_table)
                statement.addBatch(sql_command);

            for(String sql_command : create_disk_table)
                statement.addBatch(sql_command);
            statement.executeBatch();


        }
        fillEnumTables();
    }

    private void fillEnumTables() throws SQLException {
        try(Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.addBatch("INSERT INTO sokettype (_id,_name) VALUES (1,'Lga');");
            statement.addBatch("INSERT INTO sokettype (_id,_name) VALUES (2,'Pga');");
            statement.executeBatch();

            statement.addBatch("INSERT INTO ramformfactor (_id,_name) VALUES (1,'DIMM');");
            statement.addBatch("INSERT INTO ramformfactor (_id,_name) VALUES (2,'SO_DIMM');");
            statement.executeBatch();

            statement.addBatch("INSERT INTO ramtype (_id,_name) VALUES (1,'Ddr1');");
            statement.addBatch("INSERT INTO ramtype (_id,_name) VALUES (2,'Ddr2');");
            statement.addBatch("INSERT INTO ramtype (_id,_name) VALUES (3,'Ddr3');");
            statement.addBatch("INSERT INTO ramtype (_id,_name) VALUES (4,'Ddr4');");
            statement.addBatch("INSERT INTO ramtype (_id,_name) VALUES (5,'Ddr5');");
            statement.executeBatch();

            statement.addBatch("INSERT INTO formfactorhdd (_id, _name) VALUES (1,'_3_5')");
            statement.addBatch("INSERT INTO formfactorhdd (_id, _name) VALUES (2,'_2_5')");
            statement.executeBatch();

            statement.addBatch("INSERT INTO formfactorssd (_id, _name) VALUES (1,'_2_5')");
            statement.addBatch("INSERT INTO formfactorssd (_id, _name) VALUES (2,'M_2')");
            statement.addBatch("INSERT INTO formfactorssd (_id, _name) VALUES (3,'None')");
            statement.executeBatch();

            statement.addBatch("INSERT INTO memorytypessd (_id, _name) VALUES (1,'SLS')");
            statement.addBatch("INSERT INTO memorytypessd (_id, _name) VALUES (2,'MLS')");
            statement.addBatch("INSERT INTO memorytypessd (_id, _name) VALUES (3,'TLS')");
            statement.addBatch("INSERT INTO memorytypessd (_id, _name) VALUES (4,'QLS')");
            statement.addBatch("INSERT INTO memorytypessd (_id, _name) VALUES (5,'None')");
            statement.executeBatch();

            statement.addBatch("INSERT INTO pcinterfaces (_id, _name) VALUES (1,'SATA')");
            statement.addBatch("INSERT INTO pcinterfaces (_id, _name) VALUES (2,'IDE')");
            statement.addBatch("INSERT INTO pcinterfaces (_id, _name) VALUES (3,'USB')");
            statement.addBatch("INSERT INTO pcinterfaces (_id, _name) VALUES (4,'PCIE')");
            statement.addBatch("INSERT INTO pcinterfaces (_id, _name) VALUES (5,'PCI')");
            statement.executeBatch();

        }


    }
}
