package org.cis120.snake;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * 
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a circle of a specified color.
 */
public class RedDot extends GameObj {
    public static final int SIZE = 10;

    private Color color;
    private int[][] redDotTable = new int[40][40];
    private int ranx = 0;
    private int rany = 0;

    public RedDot(int courtWidth, int courtHeight, int posx, int posy) {
        super(0,0, posx, posy, SIZE, SIZE, courtWidth, courtHeight);
        ranx = posx;
        rany = posy;
        this.color = Color.red;
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                redDotTable[i][j] = 0;
            }
        }
    }



    public int getPx() {
        return ranx;
    }

    public int getPy() {
        return rany;
    }

    public void setRedDotTable(int x ,int y) {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                redDotTable[i][j] = 0;
            }
        }
        ranx = x;
        rany = y;
        redDotTable[ranx][rany] = 1;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if (redDotTable[i][j] == 1) {
                    g.fillOval(i * 10, j * 10, 10, 10);
                }

            }
        }
    }
}