package jchess.core;

import java.util.Map;
import java.util.Optional;

public record Board(
        Map<Position, Optional<Piece>> pieces) implements OnMoveListener {

    @Override
    public void onMove(
            final Player player,
            final Position origin,
            final Position destiny) {
        final var from = this.pieces().get(origin);
        final var to = this.pieces().get(destiny);
        if (to != null) {
            this.pieces().remove(destiny);
        }
        this.pieces().put(destiny, from);
    }
}
