/**
 * Created by xuran on 16/8/31.
 */
public class ListStack {
    private class Node {
        private String key;
        private Node next;
    }

    private Node first;

    public ListStack(){
        first = null;
    }

    public void push(String inputKey) {
        Node oldFirst = first;
        first = new Node();
        first.key = inputKey;
        first.next = oldFirst;
        return;
    }

    public String pop() {
        String returnStr = first.key;
        first = first.next;
        return returnStr;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
