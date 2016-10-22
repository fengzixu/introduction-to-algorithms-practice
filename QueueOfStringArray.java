import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by xuran on 2016/10/22.
 */
public class QueueOfStringArray {
    private String[] queueArray;
    private int N;
    private int head;
    private int tail;

    public QueueOfStringArray() {
        queueArray = new String[1];
        head = 0;
        tail = 0;
        N = 0;
    }

    private void resizing(int capacity) {
        String[] copy = new String[capacity];
        for (int i = head, j = 0; i < tail; i++, j++) {
            copy[j] = queueArray[i];
        }

        queueArray = copy;
        head = 0;
        tail = N;
    }

    public void enqueue(String item) {
        if (tail == queueArray.length) {
            resizing(queueArray.length * 2);
        }

        queueArray[tail++] = item;
        N++;
    }

    public String dequeue() {
        N--;
        String item = queueArray[head];
        queueArray[head] = null;
        head++;
        if (N > 0 && N == queueArray.length / 4) {
            resizing(queueArray.length / 2);
        }

        if (N == 0) {
            head = -1;
            tail = -1;
        }
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public static void main(String[] args) {
        QueueOfStringArray obj = new QueueOfStringArray();
        while (!StdIn.isEmpty()) {
            String temp = StdIn.readString();
            if (temp.equals("-")) {
                StdOut.println(obj.dequeue());
            } else {
                obj.enqueue(temp);
            }
        }
    }
}
