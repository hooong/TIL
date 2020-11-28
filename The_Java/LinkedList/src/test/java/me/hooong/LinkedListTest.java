package me.hooong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    @DisplayName("Linked List 테스트")
    void LinkedList() {
        LinkedList list = new LinkedList();

        list.add(new ListNode(4), 0);
        list.add(new ListNode(7), 0);
        list.add(new ListNode(1), 0);
        list.add(new ListNode(3), 0);
        System.out.println(list.toString());

        list.remove(3);
        System.out.println(list.toString());
        list.remove(0);
        System.out.println(list.toString());

        list.add(new ListNode(5), 1);
        System.out.println(list.toString());

        assertTrue(list.contains(new ListNode(5)));
        assertFalse(list.contains(new ListNode(9)));

    }
}