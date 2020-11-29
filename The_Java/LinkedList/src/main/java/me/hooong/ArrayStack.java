package me.hooong;

public class ArrayStack {
    int top;
    int[] stack;

    public ArrayStack(int sizeOfStack) {
        this.stack = new int[sizeOfStack];
        this.top = -1;
    }

    public void push(int data) {
        if (isFull()) throw new RuntimeException("이미 스택이 가득 참.");

        stack[++top] = data;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("스택이 비어있음.");

        return stack[top--];
    }

    private boolean isEmpty() {
        return top < 0;
    }

    private boolean isFull() {
        return top == stack.length - 1;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("|   |\n");
        for (int i = top; i >= 0; i--) {
            sb.append("| " + stack[i] + " |\n");
        }
        sb.append("-----");

        return sb.toString();
    }
}
