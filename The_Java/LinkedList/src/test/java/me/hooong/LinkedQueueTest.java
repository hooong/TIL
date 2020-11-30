package me.hooong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {

    @Test
    @DisplayName("Linked List Queue 테스트")
    void LinkedQueue() {
        LinkedQueue queue = new LinkedQueue();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.toString());

        assertEquals(1, queue.pop());
        assertEquals(2, queue.pop());
        assertEquals(3, queue.pop());
        assertThrows(RuntimeException.class, queue::pop);

    }

}