package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellComponent extends JPanel {
    private Color background;

    private GridType gridType;

    private boolean validMove;
    private boolean hovered;


    // Add this member variable to the CellComponent class


    public CellComponent(GridType gridType, Point location, int size) {
        setLayout(new GridLayout(1, 1));
        setLocation(location);
        setSize(size, size);

        this.gridType = gridType;

        // Load the image based on the background color

        switch (gridType) {
            case RIVER:
                this.background = Color.cyan;
                break;
            case LAND:
                this.background = Color.decode("#EBC79E");
                break;
            case TRAP:
                this.background = Color.pink;
                break;
            case DENS:
                this.background = Color.RED;
                break;
        }


        // Add mouse listener to this cell component
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                // Trigger the click event in ChessboardComponent
                getParent().dispatchEvent(new MouseEvent(getParent(), e.getID(), e.getWhen(), e.getModifiersEx(), getLocation().x + e.getX(), getLocation().y + e.getY(), e.getClickCount(), e.isPopupTrigger(), e.getButton()));
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                highlightGrid();
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if(validMove==false){
                    resetGridColors();
                    repaint();
                }

            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(background);
        g.fillRect(1, 1, this.getWidth()-1, this.getHeight()-1);
    }
    public void highlightGrid(){
        this.background=Color.decode("#FFFF00");
    }
    public void resetGridColors(){
        if (gridType.equals(GridType.RIVER)) {
            this.background = Color.cyan;
        } else if (gridType.equals(GridType.LAND)) {
            this.background = Color.decode("#EBC79E");
        } else if (gridType.equals(GridType.TRAP)) {
            this.background = Color.pink;
        } else if (gridType.equals(GridType.DENS)) {
            this.background = Color.RED;
        }
    }

    public void setValidMove(boolean validMove) {
        this.validMove = validMove;
        if(this.validMove==true){
            this.background=Color.decode("#FFFF00");
        }
    }


    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    // Add this setter method to the CellComponent class

}
