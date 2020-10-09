package jchess.core;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface Tile {
    Position position();

    Color color();

    Optional<Piece> piece();

    Set<Position> possibleMoves(Board board);

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
        WHITE
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

        @Override
        public Set<Position> possibleMoves(
                final Board board) {
            return piece.get().possibleMoves()
                    .stream()
                    .map(position -> position.plus(this.position()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(position -> board.tiles()[position.row()][position.column()].piece().isEmpty())
                    .collect(Collectors.toSet());
        }
    }

    record EmptyTile(
            Position position,
            Color color) implements Tile {

        @Override
        public Optional<Piece> piece() {
            return Optional.empty();
        }

        @Override
        public Set<Position> possibleMoves(
                final Board board) {
            return Set.of();
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
