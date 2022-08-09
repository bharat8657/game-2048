package ua.pp.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.Font.*;

public class Main extends GameLogic {
    static final int TILE_SIZE = 64;
    static final int TILES_MARGIN = 16;
    static final int TILE_COUNT = 4;
    static final Color BG_COLOR = new Color(0xBAB3B3);




    public Main() {
        setPreferredSize(new Dimension(offsetCoors(TILE_COUNT) + TILES_MARGIN, offsetCoors(TILE_COUNT) + TILE_SIZE * 2));
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    resetGame();
                }
                if (canMove()) {
                    myLose = true;
                }

                if (!myWin && !myLose) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            left();
                            break;
                        case KeyEvent.VK_RIGHT:
                            right();
                            break;
                        case KeyEvent.VK_DOWN:
                            down();
                            break;
                        case KeyEvent.VK_UP:
                            up();
                            break;
                    }
                }

                if (!myWin && canMove()) {
                    myLose = true;
                }

                repaint();
            }
        });
        resetGame();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int y = 0; y < TILE_COUNT; y++) {
            for (int x = 0; x < TILE_COUNT; x++) {
                drawTile(g, myTiles[x + y * TILE_COUNT], x, y);
            }
        }
    }

    private void drawTile(Graphics g2, Tile tile, int x, int y) {
        Graphics2D g = ((Graphics2D) g2);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        int value = tile.value;
        g.setColor(tile.getBackground());
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 10, 10);
        g.setColor(tile.getForeground());
        final int size = value < 100 ? 38 : value < 1000 ? 36 : 24;
        final Font font = new Font(SANS_SERIF, BOLD, size);
        g.setFont(font);

        String s = String.valueOf(value);
        final FontMetrics fm = getFontMetrics(font);

        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

        if (value != 0)
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);

        if (myWin || myLose) {
            g.setColor(new Color(255, 255, 255, 30));
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setFont(new Font(SANS_SERIF, BOLD, 48));
            if (myWin) {
                g.setColor(new Color(61, 157, 0));
                g.drawString("You won!", 68, 150);
            }
            if (myLose) {
                g.setColor(new Color(225, 0, 0));
                g.drawString("Game over!", 50, 130);
            }
            if (myWin || myLose) {
                g.setFont(new Font(SANS_SERIF, BOLD, 16));
                g.setColor(new Color(128, 128, 128, 128));
                g.drawString("Press SPACE to play again", TILE_SIZE, getHeight() - TILE_SIZE - TILES_MARGIN);
            }
        }
        g.setFont(new Font(SANS_SERIF, ITALIC, 18));
        g.drawString("Score: " + getMyScore(), getWidth() / 2 - 2 * TILES_MARGIN, getHeight() - 2 * TILES_MARGIN);

    }

    private static int offsetCoors(int arg) {
        return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
    }

    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setTitle("2048 Game");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(offsetCoors(TILE_COUNT) + TILES_MARGIN, offsetCoors(TILE_COUNT) + TILE_SIZE * 2);
        game.setResizable(false);

        game.add(new Main());

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}