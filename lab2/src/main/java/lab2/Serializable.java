package lab2;


import java.io.IOException;

public interface Serializable<T> extends java.io.Serializable {
    //void toFile(List<T> entities, String fileName) throws IOException;
    void toFile(T entity, String fileName) throws IOException;
    //List<T> listFromFile(String fileName, Class<T> cl) throws IOException;
    T fromFile(Class<T> cl, String fileName) throws IOException;
}
