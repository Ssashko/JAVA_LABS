import lab2.*;
import lab2classes.*;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSerializable {


    @Test(dataProvider = "SingleObjectProvider")
    public<T> void SingleObjectTest(Serializable<T> srl,String filename, T figure) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, ParseException {
        srl.toFile(figure, filename);
        T export = srl.fromFile(filename);
        assertEquals(export, figure);
    }

    @Test(dataProvider = "MultiObjectProvider")
    public<T> void ListObjectTest(Serializable<T> srl,String filename, List<T> figures) throws IOException, IllegalAccessException, ParseException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        srl.listToFile(figures, filename);
        List<T> export = srl.listFromFile(filename);
        assertEquals(export, figures);
    }

    @DataProvider
    public Object[][] MultiObjectProvider() {

        return new Object[][] {

                {new SerializableXml<>(Circle.class), "test",new ArrayList<Circle>(Arrays.asList(
                        new Circle(new Point(1,2),new Point(3,8)),
                        new Circle(new Point(0,0),new Point(1,2)),
                        new Circle(new Point(-1,-2),new Point(3,8))
                ))},
                {new SerializableTxt<>(Point.class), "test",new ArrayList<Point>(Arrays.asList(
                        new Point(1,2),
                        new Point(3,8),
                        new Point(0,0)
                ))},
                {new SerializableJSON<>(Triangle.class), "test",new ArrayList<Triangle>(Arrays.asList(
                        new Triangle(new Point(1,2), new Point(-1,-2), new Point(0,0)),
                        new Triangle(new Point(3,4), new Point(-4,-3), new Point(0,0)),
                        new Triangle(new Point(1,2), new Point(-1,-2), new Point(7,8))
                ))},
        };
    }

    @DataProvider
    public Object[][] SingleObjectProvider() {

        return new Object[][] {
                {new SerializableTxt<>(Point.class), "test",new Point(1,2)},
                {new SerializableJSON<>(Circle.class), "test",new Circle(new Point(1,2),new Point(3,8))},
                {new SerializableXml<>(Rectangle.class), "test",new Rectangle(new Point(1,2),new Point(3,8))}
        };
    }

}
