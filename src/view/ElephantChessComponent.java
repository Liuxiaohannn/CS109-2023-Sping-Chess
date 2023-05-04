package view;


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
//    private PlayerColor owner;
//    private boolean selected;

    public ElephantChessComponent(PlayerColor owner, int size) {
//        this.owner = owner;
//        this.selected = false;
        super(owner, size);
        setLocation(0,0);
        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Elephant/StompingElephantIdleSide.gif"));
//        setSize(size/2, size/2);

//        setVisible(true);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        Font font = new Font("楷体", Font.PLAIN, getWidth() / 2);
//        g2.setFont(font);
//        g2.setColor(owner.getColor());
//        g2.drawString("象", getWidth() / 4, getHeight() * 5 / 8); // FIXME: Use library to find the correct offset.
//        if (isSelected()) { // Highlights the model if selected.
//            g.setColor(Color.RED);
//            g.drawOval(0, 0, getWidth() , getHeight());
//        }
    }
}
