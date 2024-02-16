package figury;

import java.util.Objects;

public class Shape {
    private int x, y;
    private final int d = 60;
    private boolean done = false;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getD() {
        return d;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return x == shape.x && y == shape.y && d == shape.d && done == shape.done;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, d, done);
    }

}
