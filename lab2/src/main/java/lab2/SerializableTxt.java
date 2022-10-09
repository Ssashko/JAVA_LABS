package lab2;

import java.io.*;

public class SerializableTxt<T> implements Serializable<T>{
    /*
    @Override
    public void toFile(List<T> entities, String fileName) throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(String.join(".",fileName,"txt"));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(entities);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            if(oos != null)
                oos.close();
            if(fos != null)
                fos.close();
        }
    }
*/
    @Override
    public void toFile(T entity, String fileName) throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            String fln = String.join(".",fileName,"txt");
            fos = new FileOutputStream(fln);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(entity);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            if(oos != null)
                oos.close();
            if(fos != null)
                fos.close();
        }
    }
/*
    @Override
    public List<T> listFromFile(String fileName, Class<T> cl) throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(String.join(".",fileName,"txt"));
            ois = new ObjectInputStream(fis);
            return (List<T>)ois.readObject();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            if(fis != null)
                fis.close();
            if(ois != null)
                ois.close();
        }
        return null;
    }
*/
    @Override
    public T fromFile(Class<T> cl, String fileName) throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(String.join(".",fileName,"txt"));
            ois = new ObjectInputStream(fis);
            return cl.cast(ois.readObject());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            if(fis != null)
                fis.close();
            if(ois != null)
                ois.close();
        }
        return null;
    }
}
