package lab2;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializableJSON<T> implements Serializable<T> {
    /*
    @Override
    public void toFile(List<T> entities, String fileName) throws FileNotFoundException, JsonProcessingException {
        PrintWriter pw = null;
        try {
            File file = new File(String.join(".",fileName,"json"));
            pw = new PrintWriter(file);
            ObjectMapper mapper = new ObjectMapper();
            String listToJson = mapper.writeValueAsString(entities);
            pw.print(listToJson);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            if(pw != null)
                pw.close();
        }
    }
    */
    @Override
    public void toFile(T entity, String fileName) {
        try {

            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(new File(String.join(".",fileName,"json")), entity);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
/*
    @Override
    public List<T> listFromFile(String fileName, Class<T> cl) throws FileNotFoundException, JsonProcessingException {
        Scanner sc = null;
        try {
            File file = new File(String.join(".",fileName,"json"));
            sc = new Scanner(file);
            String jsondata = "";
            while (sc.hasNext())
                jsondata = String.join(jsondata, sc.nextLine());

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(jsondata, List.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            if(sc != null)
                sc.close();
        }
        return null;
    }
*/
    @Override
    public T fromFile(Class<T> cl, String fileName) throws IOException {
        try {
            String json = new String(Files.readAllBytes(Paths.get(String.join(".",fileName,"json"))));
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(json, cl);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
