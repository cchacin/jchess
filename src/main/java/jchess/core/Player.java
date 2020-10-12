package jchess.core;

import java.util.List;
import java.util.Map;

public record Player(
        Alliance alliance,
        Map<Position, Piece> alivePieces,
        List<Piece> deadPieces) implements OnMoveListener {

    @Override
    public void onMove(
            final Player player,
            final Position origin,
            final Position destiny) {
        this.deadPieces().add(this.alivePieces().get(destiny));
        this.alivePieces().remove(destiny);
    }
}
