import lab2.*;
import lab2classes.*;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestSerializable {
    /*
    @Test(dataProvider = "ListProvider")
    public void ListTest(Serializable<Figure> srl, String filename, List<Figure> figures) throws IOException {
        srl.toFile(figures,filename);
        List<Figure> export = (List<Figure>) srl.fromFile(filename);
        for(int i = 0;i < export.size();i++)
        {
            assertEquals(export.get(i).call(), figures.get(i).call());
        }
    }

    @DataProvider
    public Object[][] ListProvider() {

        return new Object[][] { {new SerializableJSON<Figure>(),"test",new List<Figure>() {new Circle(new Point(1,2),new Point(0,4))}}
        };
    }
     */

    @Test(dataProvider = "SingleObjectProvider")
    public<T> void SingleObjectTest(Class<T> cl,Serializable<T> srl,String filename, T figure) throws IOException {
        srl.toFile(figure, filename);
        T export = srl.fromFile(cl, filename);
        assertEquals(export, figure);
    }


    @DataProvider
    public Object[][] SingleObjectProvider() {

        return new Object[][] {
                {Point.class, new SerializableTxt<>(), "test",new Point(1,2)},
                {Circle.class, new SerializableJSON<>(), "test",new Circle(new Point(1,2),new Point(3,8))},
                {Rectangle.class, new SerializableXml<>(), "test",new Rectangle(new Point(1,2),new Point(3,8))}
        };
    }

}
