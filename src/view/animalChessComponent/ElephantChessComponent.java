package view.animalChessComponent;

import model.PlayerColor;
import view.ChessComponent;

import javax.swing.*;
import java.awt.*;

/**
 * This is the equivalent of the ChessPiece class,
 * but this class only cares how to draw Chess on ChessboardComponent
 */
public class ElephantChessComponent extends ChessComponent {
    private ImageIcon gifImage;

    public ElephantChessComponent(PlayerColor owner, int size) {
        super(owner, size);

        // Load the GIF image
        gifImage = new ImageIcon(getClass().getResource("\u202AC:\\Users\\86131\\Desktop\\CS109 JavaA  弱智斗兽棋\\Animal\\Stomping Elephant\\StompingElephantIdleSide.gif"));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the GIF image
        if (gifImage != null) {
            g.drawImage(gifImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
