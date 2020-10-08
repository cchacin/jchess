package jchess.core;

import java.util.Optional;

public record Position(
        int row,
        int column) {

    public static Position of(
            final int row,
            final int column) {
        return new Position(row, column);
    }

    public Optional<Position> plus(
            final Position position) {
        final var newPosition = Position.of(
                this.row() + position.row(),
                this.column() + position.column()
        );
        return newPosition.isValid() ?
                Optional.empty() :
                Optional.of(newPosition);
    }

    private boolean isValid() {
        return row() > 7 || row() < 0 || column() > 7 || column() < 0;
    }
}
