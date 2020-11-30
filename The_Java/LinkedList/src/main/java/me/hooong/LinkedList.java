package me.hooong;

import java.util.List;

public class LinkedList {

    private ListNode head;
    private ListNode tail;
    private int numberOfNode;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.numberOfNode = 0;
    }

    public ListNode getHead() {
        return head;
    }

    public int getNumberOfNode() {
        return numberOfNode;
    }

    public ListNode add(ListNode nodeToAdd, int position) {
        if (position < 0) {
            throw new RuntimeException("Index Error");
        }
        else if (position == 1) {
            return addFirst(nodeToAdd);
        } else if (position == numberOfNode) {
            return addLast(nodeToAdd);
        }
        else {
            return addMiddle(nodeToAdd, position);
        }
    }

    public ListNode addFirst(ListNode nodeToAdd) {
        if (this.head == null) {
            this.tail = nodeToAdd;
        }
        nodeToAdd.setNext(this.head);
        this.head = nodeToAdd;
        this.numberOfNode++;

        return nodeToAdd;
    }

    public ListNode addLast(ListNode nodeToAdd) {
        ListNode lastNode = this.tail;

        if (lastNode == null) {
            this.head = nodeToAdd;
        } else {
            lastNode.setNext(nodeToAdd);
        }
        this.tail = nodeToAdd;
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
        else if (positionToRemove == 1) {
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

    public ListNode removeLast(int position) {
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

        for (int i = 0; i < position - 2; i++) {
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

    public boolean isEmpty() {
        return numberOfNode == 0;
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
