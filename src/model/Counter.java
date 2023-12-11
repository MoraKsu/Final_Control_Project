package model;

public class Counter implements AutoCloseable {
    private int count;

    public Counter() {
        this.count = 0;
    }

    public void add() {
        count++;
    }

    @Override
    public void close() {
        if (count > 0) {
            throw new IllegalStateException("Работа с объектом типа Counter должна быть в блоке try-with-resources.");
        }
    }
}
