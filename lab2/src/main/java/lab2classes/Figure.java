package lab2classes;

import java.io.Serializable;
import java.util.Objects;

public interface Figure extends Serializable {
    String call();
    double getSquare();
    double getPerimeter();
    @Override
    boolean equals(Object obj);

}
