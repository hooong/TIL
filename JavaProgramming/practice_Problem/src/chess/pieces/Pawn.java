package chess.pieces;

public class Pawn {
    final String COLOR_WHITE = "White";
    final String COLOR_BLACK = "Black";

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
