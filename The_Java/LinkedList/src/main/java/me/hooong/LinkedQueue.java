package me.hooong;

public class LinkedQueue {
    LinkedList queue;

    public LinkedQueue() {
        this.queue = new LinkedList();
    }

    public void push(int data) {
        queue.addFirst(new ListNode(data));
    }

    public int pop() {
        return queue.remove(queue.getNumberOfNode()).getData();
    }

    private boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
