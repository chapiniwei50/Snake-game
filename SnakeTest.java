package org.cis120.snake;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class SnakeTest {
    @Test
    public void testSnakeBodyTaken() {
        Snake testSnake = new Snake(0, 0, 10, 10, 400,
                400);
        testSnake.move();
        assertTrue(testSnake.snakeBodyTaken(10,9));
    }
    @Test
    public void testHitItself() {
        Snake testSnake = new Snake(0, 0, 10, 10, 400,
                400);
        for (int i = 0 ; i < 10; i++) {
            testSnake.growLength();
        }
        testSnake.setVx(-10);
        testSnake.setVy(0);
        testSnake.move();
        testSnake.setVx(0);
        testSnake.setVy(10);
        testSnake.move();
        assertFalse(testSnake.ifHitItself());
    }

    @Test
    public void testHitWall() {
        Snake testSnake = new Snake(0, 0, 10, 10, 400,
                400);
        for (int i = 0; i < 10; i++) {
            testSnake.move();
        }
        assertTrue(testSnake.ifHitWall());
    }

    @Test
    public void testIntersect() {
        Snake testSnake = new Snake(0, 0, 10, 10, 400,
                400);
        testSnake.move();
        RedDot redDot = new RedDot(400, 400, 10, 9);

        assertTrue(testSnake.intersects(redDot));
    }

    @Test
    public void testGrowLength() {
        Snake testSnake = new Snake(0, 0, 10, 10, 400,
                400);
        testSnake.move();
        testSnake.growLength();

        assertTrue(testSnake.snakeBodyTaken(10,9));
    }
}


