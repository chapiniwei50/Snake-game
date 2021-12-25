package org.cis120.snake;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * 
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.util.*;

public class Snake extends GameObj {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    private int headX;
    private int headY;
    private int length;
    private int dir;
    private Color color;
    private int[][] snakeBody = new int[40][40];

    public Snake(int xPos, int yPos, int headx, int heady, int courtWidth, int courtHeight) {
        super(0, 10, xPos, yPos, 20,20, courtWidth, courtHeight);
        color = Color.green;
        headX = headx;
        headY = heady;
        length = 2;
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                snakeBody[i][j] = 0;
            }
        }
    }

    public void setDir(int d) {
        dir = d;
    }

    public int getLength() {
        return length;
    }

    public void growLength() {
        int x = headX;
        int y = headY;
        for (int i = 0; i <= length; i++) {
            if (snakeBody[x][y] == LEFT) {
                x++;
            } else if (snakeBody[x][y] == RIGHT) {
                x--;
            } else if (snakeBody[x][y] == UP) {
                y++;
            } else if (snakeBody[x][y] == DOWN) {
                y--;
            }
        }

        if (snakeBody[x][y] == LEFT) {
            snakeBody[x - 1][y] = LEFT;
        } else if (snakeBody[x][y] == RIGHT) {
            snakeBody[x + 1][y] = RIGHT;
        } else if (snakeBody[x][y] == UP) {
            snakeBody[x][y + 1] = UP;
        } else if (snakeBody[x][y] == DOWN) {
            snakeBody[x][y - 1] = UP;
        }
        length++;
    }

    @Override
    public boolean intersects(GameObj dot) {
        return headX == dot.getPx() && headY == dot.getPy();
    }

    @Override
    public void move() {
        int vx = this.getVx();
        int vy = this.getVy();
        int d = 0;
        if (vx == -10) {
            headX--;
            d = LEFT;
        } else if (vx == 10) {
            headX++;
            d = RIGHT;
        } else if (vy == 10) {
            headY--;
            d = UP;
        } else if (vy == -10) {
            headY++;
            d = DOWN;
        }
        if (snakeBody[headX][headY] <= 0) {
            snakeBody[headX][headY] = d;
            int x = headX;
            int y = headY;
            for (int i = 0; i <= length; i++) {
                if (snakeBody[x][y] == LEFT) {
                    x++;
                } else if (snakeBody[x][y] == RIGHT) {
                    x--;
                } else if (snakeBody[x][y] == UP) {
                    y++;
                } else if (snakeBody[x][y] == DOWN) {
                    y--;
                }
            }
            snakeBody[x][y] = 0;
        }
    }

    public boolean ifHitItself() {

        int vx = this.getVx();
        int vy = this.getVy();
        boolean hit = false;
        if (vx == -10) {
            hit = snakeBody[headX - 1][headY] > 0;
        } else if (vx == 10) {
            hit = snakeBody[headX + 1][headY] > 0;
        } else if (vy == 10) {
            hit = snakeBody[headX][headY - 1] > 0;
        } else if (vy == -10) {
            hit = snakeBody[headX][headY + 1] > 0;
        }
        return hit;
    }


    public boolean ifHitWall() {
        return ((headX - 1) < 0 || (headX + 1) >= 40 ||
                (headY - 1) < 0 || (headY + 1) >= 40);
    }
    public boolean snakeBodyTaken(int x, int y) {
        return (snakeBody[x][y] > 0);
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                g.setColor(this.color);
                if (snakeBody[i][j] == 1) {
                    g.setColor(this.color);
                    g.fillOval(i * 10, j * 10, 10, 10);
                }
                if (snakeBody[i][j] == 2) {
                    g.setColor(Color.orange);
                    g.fillOval(i * 10, j * 10, 10, 10);
                }
                if (snakeBody[i][j] == 3) {
                    g.setColor(Color.blue);
                    g.fillOval(i * 10, j * 10, 10, 10);
                }
                if (snakeBody[i][j] == 4) {
                    g.setColor(Color.yellow);
                    g.fillOval(i * 10, j * 10, 10, 10);
                }
            }
        }
    }
}
