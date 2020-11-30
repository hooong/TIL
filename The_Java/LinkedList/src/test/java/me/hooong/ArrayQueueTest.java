package me.hooong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    @Test
    @DisplayName("원형 큐 테스트")
    void ArrayQueue() {
        ArrayQueue queue = new ArrayQueue(5);

        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.toString());

        assertEquals(1, queue.pop());
        assertEquals(2, queue.pop());
        assertEquals(3, queue.pop());
    }

    @Test
    @DisplayName("꽉참 테스트")
    void ArrayQueueIsFullTest() {
        ArrayQueue queue = new ArrayQueue(5);

        for (int i = 0; i < 4; i++) {
             queue.push(i);
        }

        assertThrows(RuntimeException.class, () -> {
            queue.push(1);
        });
    }

    @Test
    @DisplayName("비어있음 테스트")
    void ArrayQueueIsEmptyTest() {
        ArrayQueue queue = new ArrayQueue(5);

        assertThrows(RuntimeException.class, queue::pop);
    }

}