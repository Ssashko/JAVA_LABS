package lab2;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializableJSON<T> implements Serializable<T> {
    private final Class<T> type;
    public SerializableJSON(Class<T> _type) {
        type = _type;
    }
    @Override
    public Class<T> getGenericClass() {
        return type;
    }

    @Override
    public void listToFile(List<T> entity, String fileName) throws IOException {
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(String.join(".",fileName,"json")), entity);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void toFile(T entity, String fileName) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(String.join(".",fileName,"json")), entity);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public List<T> listFromFile(String fileName) throws FileNotFoundException {
        try {
            String json = new String(Files.readAllBytes(Paths.get(String.join(".",fileName,"json"))));
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, getGenericClass());
            return mapper.readValue(json, type);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public T fromFile(String fileName) throws IOException {
        try {
            String json = new String(Files.readAllBytes(Paths.get(String.join(".",fileName,"json"))));
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(json, getGenericClass());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
