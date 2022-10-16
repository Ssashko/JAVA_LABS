package lab2;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

public interface Serializable<T> {

    Class<T> getGenericClass();
    void listToFile(List<T> entity, String fileName) throws IOException, IllegalAccessException;
    void toFile(T entity, String fileName) throws IOException, IllegalAccessException;

    List<T> listFromFile(String fileName) throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

    T fromFile(String fileName) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ParseException;
}
