package view;


import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

/**
 * This is the equivalent of the ChessPiece class,
 * but this class only cares how to draw Chess on ChessboardComponent
 */
public class ChessComponent extends JComponent {
    protected PlayerColor owner;


    protected boolean selected;

    public ChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
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
        // 如果棋子是蓝方的，就画一个蓝色的环
        if (owner == PlayerColor.BLUE) {
            g.setColor(Color.BLUE);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
        // 如果棋子是红方的，就画一个红色的环
        else if (owner == PlayerColor.RED) {
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.BLACK);
            g.drawOval(0, 0, getWidth(), getHeight());
            g.drawOval(1, 1, getWidth() - 1, getHeight() - 1);
            g.drawOval(2, 2, getWidth() - 2, getHeight() - 2);
        }
    }
}//画圈（棋子）
class CatChessComponent extends ChessComponent{
    private ImageIcon gifImage;
    public CatChessComponent(PlayerColor owner, int size) {
        super(owner, size);
        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Cat/MeowingCatIdleSide.gif"));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gifImage != null) {
            g.drawImage(gifImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
class DogChessComponent extends ChessComponent{
    private ImageIcon gifImage;

    public DogChessComponent(PlayerColor owner, int size) {
        super(owner, size);

        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Dog/FaithfulDogIdleSide.gif"));

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gifImage != null) {
            g.drawImage(gifImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
 class ElephantChessComponent extends ChessComponent {
    private ImageIcon gifImage;

    public ElephantChessComponent(PlayerColor owner, int size) {
        super(owner, size);

        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Elephant/StompingElephantIdleSide.gif"));

    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gifImage != null) {
            g.drawImage(gifImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
class LeopardChessComponent extends ChessComponent {
    private ImageIcon gifImage;

    public LeopardChessComponent(PlayerColor owner, int size) {
        super(owner, size);
        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Leopard(Camel)/ThirstyCamelIdleSide.gif"));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (gifImage != null) {
            g.drawImage(gifImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
class LionChessComponent extends ChessComponent {
    private ImageIcon gifImage;

    public LionChessComponent(PlayerColor owner, int size) {
        super(owner, size);
        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Lion(Moose)/MajesticMooseIdleSide.gif"));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (gifImage != null) {
            g.drawImage(gifImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
class RatChessComponent extends ChessComponent {
    private ImageIcon gifImage;

    public RatChessComponent(PlayerColor owner, int size) {
        super(owner, size);
        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Mouse(Skunk)/StinkySkunkIdleSide.gif"));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gifImage != null) {
            g.drawImage(gifImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
class TigerChessComponent extends ChessComponent {
    private ImageIcon gifImage;

    public TigerChessComponent(PlayerColor owner, int size) {
        super(owner, size);
        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Tiger( Bear)/CaveBearIdleSide.gif"));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gifImage != null) {
            g.drawImage(gifImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
class WolfChessComponent extends ChessComponent {
    private ImageIcon gifImage;

    public WolfChessComponent(PlayerColor owner, int size) {
        super(owner, size);
        gifImage = new ImageIcon(getClass().getResource("/Animal picture/Wolf/TimberWolfIdleSide.gif"));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gifImage != null) {
            g.drawImage(gifImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}



