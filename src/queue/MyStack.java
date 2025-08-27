package queue;

import java.util.LinkedList;

public class MyStack {
    LinkedList<Integer> linkedList = new LinkedList<>();
    public MyStack() {

    }

    public void push(int x) {
        int n = linkedList.size();
        linkedList.offer(x);
        for (int i = 0; i < n; i++) {
            linkedList.offer(linkedList.poll());
        }
    }

    public int pop() {
       return linkedList.pop();
    }

    public int top() {
        return linkedList.peekFirst();
    }

    public boolean empty() {
       return linkedList.isEmpty();
    }
    public static void main(String[] args) {

    }
}
