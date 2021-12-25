package org.cis120.snake;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * 
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * GameCourt
 *
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {


    private Snake snake;

    private boolean playing = false;
    private JLabel status;

    public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 400;
    public static final int INTERVAL = 200;
    private RedDot redDot;


    public GameCourt(JLabel status) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (snake.getVx() != 10) {
                        snake.setDir(1);
                        snake.setVx(-10);
                        snake.setVy(0);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (snake.getVx() != -10) {
                        snake.setDir(2);
                        snake.setVx(10);
                        snake.setVy(0);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (snake.getVy() != -10) {
                        snake.setDir(3);
                        snake.setVx(0);
                        snake.setVy(10);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (snake.getVy() != 10) {
                        snake.setDir(4);
                        snake.setVx(0);
                        snake.setVy(-10);
                    }

                }
            }

        });

        this.status = status;
    }



    public void appear() {
        int ranx = (int) (Math.random() * 39);
        int rany = (int) (Math.random() * 39);
        if (snake.snakeBodyTaken(ranx, rany)) {
            appear();
        } else {
            redDot.setRedDotTable(ranx, rany);
        }
    }
    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        snake = new Snake(200,200, 20, 20, COURT_WIDTH, COURT_HEIGHT);
        redDot = new RedDot(COURT_WIDTH, COURT_HEIGHT, 0, 0);
        appear();
        playing = true;
        status.setText("running...");
        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        if (playing) {
            if (snake.ifHitWall()) {
                playing = false;
                status.setText("You lose!! score: " + (snake.getLength() - 2));
            } else if (snake.ifHitItself()) {
                playing = false;
                status.setText("You lose!! score: " + (snake.getLength() - 2));
            } else {
                snake.move();
            }
            if (snake.intersects(redDot)) {
                appear();
                snake.growLength();
                status.setText("score: " + (snake.getLength() - 2));
            }

            repaint();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        redDot.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}