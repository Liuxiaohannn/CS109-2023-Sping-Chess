package model;

import java.io.Serializable;

/**
 * This class represents positions on the checkerboard, such as (0, 0), (0, 7), and so on
 * Where, the upper left corner is (0, 0), the lower left corner is (7, 0), the upper right corner is (0, 7), and the lower right corner is (7, 7).
 */
public record ChessboardPoint(int row, int col) implements Serializable {

    public ChessboardPoint getNeighbor(int k) {
        return switch (k) {
            case 0 -> new ChessboardPoint(row - 1, col);
            case 1 -> new ChessboardPoint(row, col + 1);
            case 2 -> new ChessboardPoint(row + 1, col);
            case 3 -> new ChessboardPoint(row, col - 1);
            default -> null;
        };
    }

    @Override
    @SuppressWarnings("ALL")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ChessboardPoint temp = (ChessboardPoint) obj;
        return (temp.row() == this.row) && (temp.col() == this.col);
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ") ";
    }
}
