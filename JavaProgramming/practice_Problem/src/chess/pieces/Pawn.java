package chess.pieces;

public class Pawn {
    final String COLOR_WHITE = "p";
    final String COLOR_BLACK = "P";

    private String color;

    Pawn (String color) {
        this.color = color;
    }

    public Pawn() {
        color = COLOR_WHITE;
    }

    String getColor() {
        return color;
    }
}
