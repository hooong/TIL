package me.hooong;

public class ArrayQueue {
    private int[] queue;
    private int size;
    private int front;
    private int rear;

    public ArrayQueue(int sizeOfQueue) {
        this.queue = new int[sizeOfQueue];
        this.size = sizeOfQueue;
        this.front = sizeOfQueue - 1;
        this.rear = sizeOfQueue - 1;
    }

    public void push(int data) {
        if (isFull()) throw new RuntimeException("Queue is Full");

        front = (front + 1) % size;
        queue[front] = data;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("Queue is Empty");

        rear = (rear + 1) % size;
        return queue[rear];
    }

    private boolean isEmpty() {
        return front == rear;
    }

    private boolean isFull() {
        return rear == ((front + 1) % size);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("-> | ");
        int tmp = front;
        while (tmp != rear) {
            sb.append(queue[tmp] + " | ");

            tmp = tmp - 1;
            if (tmp < 0) tmp += size;
        }
        sb.append(" ->");

        return sb.toString();
    }
}
