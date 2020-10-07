package jchess;

import java.util.Optional;

public interface Tile {
    Position position();

    Color color();

    Optional<Piece> piece();

    static Tile empty(
            final Position position,
            final Color color) {
        return create(null, position, color);
    }

    static Tile create(
            final Piece piece,
            final Position position,
            final Color color) {
        return piece != null ?
                OccupiedTile.create(Optional.of(piece), position, color) :
                EmptyTile.create(position, color);
    }

    enum Color {
        BLACK,
        WHITE;
    }

    record OccupiedTile(
            Optional<Piece> piece,
            Position position,
            Color color) implements Tile {
        static OccupiedTile create(
                final Optional<Piece> piece,
                final Position position,
                final Color color) {
            return new OccupiedTile(piece, position, color);
        }

        @Override
        public String toString() {
            return this.color().toString().charAt(0) + " " + piece.get().getClass().getSimpleName().charAt(0);
        }
    }

    record EmptyTile(
            Position position,
            Color color) implements Tile {

        @Override
        public Optional<Piece> piece() {
            return Optional.empty();
        }

        static EmptyTile create(
                final Position position,
                final Color color) {
            return new EmptyTile(position, color);
        }

        @Override
        public String toString() {
            return this.color().toString().charAt(0) + "  ";
        }
    }
}
