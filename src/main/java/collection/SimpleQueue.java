package collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int sizeIn;
    private int sizeOut;

    public T poll() {
        if (sizeOut == 0) {
            if (sizeIn == 0) {
                throw new NoSuchElementException("Queue is Empty");
            }
            for (int i = 0; i < sizeIn; i++) {
                output.push(input.pop());
                sizeOut++;
            }
            sizeIn = 0;
        }
        sizeOut--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        sizeIn++;
    }
}
