package view.AnimalChessComponent;

import model.PlayerColor;
import view.ChessComponent;

import javax.swing.*;
import java.awt.*;

public class CatChessComponent extends ChessComponent {
    private ImageIcon gifImage;
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public CatChessComponent(PlayerColor owner, int size) {
        super(owner, size);
        setLocation(0,0);
        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Cat/MeowingCatIdleSide.gif"));
    }

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
