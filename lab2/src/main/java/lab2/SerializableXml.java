package lab2;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SerializableXml<T> implements Serializable<T>  {
/*
    @Override
    public void toFile(List<T> entities, String fileName) throws FileNotFoundException, JsonProcessingException {
        PrintWriter pw = null;
        try {
            File file = new File(String.join(".",fileName,"xml"));
            pw = new PrintWriter(file);
            XmlMapper mapper = new XmlMapper();
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
            XmlMapper mapper = new XmlMapper();
            mapper.writeValue(new File(String.join(".",fileName,"xml")), entity);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
/*
    @Override
    public List<T> listFromFile(String fileName, Class<T> cl) throws FileNotFoundException, JsonProcessingException {
        Scanner sc = null;
        try {
            File file = new File(String.join(".",fileName,"xml"));
            sc = new Scanner(file);
            String jsondata = "";
            while (sc.hasNext())
                jsondata = String.join(jsondata, sc.nextLine());
            TypeReference<List<T>> listType = new TypeReference<>() {};
            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(jsondata, listType);
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
            String xml = new String(Files.readAllBytes(Paths.get(String.join(".",fileName,"xml"))));
            XmlMapper mapper = new XmlMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(xml, cl);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
