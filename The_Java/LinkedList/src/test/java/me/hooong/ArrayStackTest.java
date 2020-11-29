package me.hooong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    @DisplayName("int 배열로 만든 Stack 테스트")
    void ArrayStack() {
        ArrayStack stack = new ArrayStack(10);

        stack.push(1);
        stack.push(2);
        System.out.println(stack.toString());

        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        System.out.println(stack.toString());
    }

    @Test
    @DisplayName("stack 에러 검출 테스트")
    void ExceptionTestOfArrayStack() {
        ArrayStack stack = new ArrayStack(3);

        assertThrows(RuntimeException.class, () -> {
            stack.pop();
        });

        for (int i = 0; i < 3; i++) {
            stack.push(i);
        }

        assertThrows(RuntimeException.class, () -> {
            stack.push(1);
        });
    }

}