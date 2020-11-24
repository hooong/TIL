package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pawn> pawnList = new ArrayList<>();

    public Board() {
        for (int i=0; i<8; i++) {
            pawnList.add();
        }
    }

    public void addPiece() {
        pawnList.add(new Pawn());
    }

    public int getCountOfPieces() {
        return pawnList.size();
    }
}
