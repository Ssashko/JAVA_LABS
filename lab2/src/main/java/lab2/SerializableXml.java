package lab2;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class SerializableXml<T> implements Serializable<T> {

    private final Class<T> type;

    public SerializableXml(Class<T> _type) {
        type = _type;
    }

    @Override
    public Class<T> getGenericClass() {
        return type;
    }
    /**
     * transferred file name should be without extensions
     * */
    @Override
    public void listToFile(List<T> entity, String fileName) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(new File(String.join(".", fileName, "xml")), entity);
    }
    /**
     * transferred file name should be without extensions
     * */
    @Override
    public void toFile(T entity, String fileName) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(new File(String.join(".", fileName, "xml")), entity);
    }

    /**
     * transferred file name should be without extensions
     * */
    @Override
    public List<T> listFromFile(String fileName) throws IOException {
        String xml = new String(Files.readAllBytes(Paths.get(String.join(".", fileName, "xml"))));
        XmlMapper mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, getGenericClass());
        return mapper.readValue(xml, type);
    }
    /**
     * transferred file name should be without extensions
     * */
    @Override
    public T fromFile(String fileName) throws IOException {
        String xml = new String(Files.readAllBytes(Paths.get(String.join(".", fileName, "xml"))));
        XmlMapper mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(xml, getGenericClass());
    }
}
