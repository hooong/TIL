package me.hooong;

public class LinkedStack {
    LinkedList stack = new LinkedList();

    public void push(int data) {
        stack.addLast(new ListNode(data));
    }

    public int pop() {
        ListNode removedNode = stack.remove(stack.getNumberOfNode());
        return removedNode.getData();
    }

    @Override
    public String toString() {
//        StringBuffer sb = new StringBuffer();
//        ListNode tmpNode = stack.getHead();
//
//        sb.append("|   |\n");
//        if (tmpNode == null) return sb.append("-----").toString();
//
//        while (tmpNode.getNext() != null) {
//            sb.append("| " + tmpNode.getData() + " |\n");
//            tmpNode = tmpNode.getNext();
//        }
//        sb.append("| " + tmpNode.getData() + " |\n");
//        sb.append("-----");
//
//        return sb.toString();
        return stack.toString();
    }
}
