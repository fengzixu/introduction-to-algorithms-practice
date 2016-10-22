import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by xuran on 2016/10/22.
 */
public class QueueOfStringsList {
    private int N;
    private class Node {
        private Node next;
        private String key;
    }
    private Node first;
    private Node end;
    public QueueOfStringsList() {
        first = null;
        end = null;
    }

    public void enqueue(String item) {
        Node insert = new Node();
        insert.key = item;
        insert.next = null;
        Node oldLast = end;
        end = insert;
        if (isEmpty()) {
            first = end;
        } else {
            oldLast.next = insert;
        }
        N++;
    }

    public String dequeue() {
        String outItem = first.key;
        first = first.next;
        if (isEmpty()) {
            end = null;
        }

        N--;
        return outItem;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        QueueOfStringsList obj = new QueueOfStringsList();
        while (!StdIn.isEmpty()) {
            String temp = StdIn.readString();
            if (temp.equals("-")) {
                StdOut.println(obj.dequeue());
            } else {
                obj.enqueue(temp);
            }
        }

        StdOut.printf("The queue has %d items", obj.size());
    }
}
