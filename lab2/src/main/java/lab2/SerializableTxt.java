package lab2;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class SerializableTxt<T> implements Serializable<T>{
    private final Class<T> type;
    public SerializableTxt(Class<T> _type) {
        type = _type;
    }
    @Override
    public Class<T> getGenericClass() {
        return type;
    }

    @Override
    public void listToFile(List<T> entity, String fileName) throws IOException, IllegalAccessException {
        FileWriter fileWriter = new FileWriter(String.join(".",fileName,"csv"));
        PrintWriter printWriter = new PrintWriter(fileWriter);

        Field[] classFields = getGenericClass().getDeclaredFields();

        String previousSeparator = "";
        for(Field classField : classFields)
        {
            printWriter.print(String.join(previousSeparator,"", classField.getName()));
            previousSeparator = ",";
        }
        printWriter.print("\n");

        for(T _entity : entity) {
            previousSeparator = "";
            for (Field classField : classFields) {
                classField.setAccessible(true);
                Object value = classField.get(_entity);
                printWriter.print(String.join(previousSeparator, "", value.toString()));
                previousSeparator = ",";
            }
            printWriter.print("\n");
        }
        printWriter.close();
    }

    @Override
    public void toFile(T entity, String fileName) throws IOException, IllegalAccessException {
        FileWriter fileWriter = new FileWriter(String.join(".",fileName,"csv"));
        PrintWriter printWriter = new PrintWriter(fileWriter);

        Field[] classFields = getGenericClass().getDeclaredFields();

        String previousSeparator = "";
        for(Field classField : classFields)
        {
            printWriter.print(String.join(previousSeparator,"", classField.getName()));
            previousSeparator = ",";
        }
        printWriter.print("\n");

        previousSeparator = "";
        for(Field classField : classFields)
        {
            classField.setAccessible(true);
            Object value = classField.get(entity);
            printWriter.print(String.join(previousSeparator,"", value.toString()));
            previousSeparator = ",";
        }
        printWriter.print("\n");

        printWriter.close();
    }

    @Override
    public List<T> listFromFile(String fileName) throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String csv = new String(Files.readAllBytes(Paths.get(String.join(".",fileName,"csv"))));
        String[] rawTypeValues = csv.split("\n");
        String[][] rawValues = new String[rawTypeValues.length][];

        for(int i = 0; i < rawValues.length;i++)
        {
            rawValues[i] = rawTypeValues[i].split(",");
        }
        final int countField = rawValues[0].length;
        final int countRecord = rawValues.length;
        String[][] rawValuesT = new String[countField][countRecord];

        for(int i = 0; i < countField;i++)
            for(int j = 0; j < countRecord;j++)
                rawValuesT[i][j] = rawValues[j][i];


        List<List<String>> values = new ArrayList<>();

        for(int i = 0; i < countField;i++) {
            values.add(Arrays.asList(rawValuesT[i]));
        };

        Comparator<List<String>> attrComp = Comparator.comparing(v -> v.get(0));
        Collections.sort(values,attrComp);



        Field[] classFields = getGenericClass().getDeclaredFields();
        List<T> obj = new ArrayList<>(countRecord-1);
        for(int i=0;i < countRecord-1;i++)
        {
            obj.add(getGenericClass().getConstructor().newInstance());
        }
        List<String> sval = new ArrayList<>(values.get(0));
        int i = 0;
        for(Field classField : classFields)
        {

            sval.set(0,classField.getName());
            int key = Collections.binarySearch(values,sval, attrComp);
            if(key < 0)
                throw new ParseException("undefined field", i);
            classField.setAccessible(true);
            for(int j=1;j < countRecord;j++) {
                double val = Double.parseDouble(values.get(key).get(j));
                classField.setDouble(obj.get(j-1),val);
            }

            i++;
        }

        return obj;
    }

    @Override
    public T fromFile(String fileName) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ParseException {
        String csv = new String(Files.readAllBytes(Paths.get(String.join(".",fileName,"csv"))));
        String[] rawTypeValues = csv.split("\n");
        String[][] rawValues = new String[rawTypeValues.length][];
        for(int i = 0; i < rawValues.length;i++)
        {
            rawValues[i] = rawTypeValues[i].split(",");
        }
        String[][] rawValuesT = new String[rawValues[0].length][rawValues.length];

        for(int i = 0; i < rawValues[0].length;i++)
            for(int j = 0; j < rawValues.length;j++)
                rawValuesT[i][j] = rawValues[j][i];


        List<List<String>> values = new ArrayList<>();

        for(int i = 0; i < rawValues[0].length;i++) {
            values.add(Arrays.asList(rawValuesT[i]));
        };

        Comparator<List<String>> attrComp = Comparator.comparing(v -> v.get(0));
        Collections.sort(values,attrComp);



        Field[] classFields = getGenericClass().getDeclaredFields();

        T obj = getGenericClass().getConstructor().newInstance();
        List<String> sval = new ArrayList<>(values.get(0));
        int i = 0;
        for(Field classField : classFields)
        {

            sval.set(0,classField.getName());
            int key = Collections.binarySearch(values,sval, attrComp);
            if(key < 0)
                throw new ParseException("undefined field", i);
            classField.setAccessible(true);
            double val = Double.parseDouble(values.get(key).get(1));
            classField.setDouble(obj,val);
            i++;
        }

        return obj;
    }
}
