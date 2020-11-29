package me.hooong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    @Test
    @DisplayName("LinkedList Stack 테스트")
    void LinkedStack() {
        LinkedStack stack = new LinkedStack();

        stack.push(3);
        stack.push(2);
        stack.push(6);
        System.out.println(stack.toString());

        assertEquals(6, stack.pop());
        assertEquals(2, stack.pop());
        System.out.println(stack.toString());
        assertEquals(3, stack.pop());
        assertThrows(RuntimeException.class, () -> {
            stack.pop();
        });
        System.out.println(stack.toString());
    }

}