package jchess.core;

import java.util.List;

public record Player(
        Alliance alliance,
        List<Piece> alivePieces,
        List<Piece> deadPieces) implements OnMoveListener {

    @Override
    public void onMove(
            final MoveEvent moveEvent) {
    }
}
