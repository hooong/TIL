package me.hooong;

import java.util.List;

public class LinkedList {

    private ListNode head;
    private int numberOfNode;

    public LinkedList() {
        this.head = null;
        this.numberOfNode = 0;
    }

    public ListNode getHead() {
        return head;
    }

    public ListNode add(ListNode nodeToAdd, int position) {
        if (position < 0) {
            throw new RuntimeException("Index Error");
        }
        else if (position == 0) {
            return addFirst(nodeToAdd);
        } else {
            return addMiddle(nodeToAdd, position);
        }
    }

    private ListNode addFirst(ListNode nodeToAdd) {
        nodeToAdd.setNext(this.head);
        this.head = nodeToAdd;
        this.numberOfNode++;

        return nodeToAdd;
    }

    private ListNode addMiddle(ListNode nodeToAdd, int position) {
        ListNode preNode = searchNode(position);

        nodeToAdd.setNext(preNode.getNext());
        preNode.setNext(nodeToAdd);
        this.numberOfNode++;

        return nodeToAdd;
    }

    public ListNode remove(int positionToRemove) {
        if (head == null) {
            throw new RuntimeException("Empty List");
        }

        if (positionToRemove < 0) {
            throw new RuntimeException("Index Error");
        }
        else if (positionToRemove == 0) {
            return removeFirst();
        } else {
            return removeLast(positionToRemove);
        }
    }

    private ListNode removeFirst() {
        ListNode removedNode = this.head;
        this.head = removedNode.getNext();
        this.numberOfNode--;

        return removedNode;
    }

    private ListNode removeLast(int position) {
        ListNode preNode = searchNode(position);
        ListNode removedNode = preNode.getNext();

        preNode.setNext(removedNode.getNext());
        this.numberOfNode--;

        return removedNode;
    }

    private ListNode searchNode(int position) {
        ListNode preNode = this.head;

        if (numberOfNode < position) {
            throw new RuntimeException("Index Error");
        }

        for (int i = 0; i < position - 1; i++) {
            preNode = preNode.getNext();
        }

        return preNode;
    }

    public boolean contains(ListNode nodeToCheck) {
        ListNode tmpNode = head;

        if (head == null) {
            throw new RuntimeException("Empty List");
        }

        while (!(tmpNode.getNext() == null)) {
            if (tmpNode.getData() == nodeToCheck.getData())
                return true;

            tmpNode = tmpNode.getNext();
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        ListNode tmpNode = head;

        if (head == null)
            return sb.toString();

        while (!(tmpNode.getNext() == null)) {
            sb.append(tmpNode.getData());
            sb.append(" -> ");
            tmpNode = tmpNode.getNext();
        }
        sb.append(tmpNode.getData());

        return sb.toString();
    }
}
