package lab2classes;

import java.util.Objects;

public interface Figure {
    String call();
    double getSquare();
    double getPerimeter();
    @Override
    boolean equals(Object obj);

}
