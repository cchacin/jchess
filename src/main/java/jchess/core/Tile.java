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
            final Position position) {
        return Tile.create(Optional.empty(), position);
    }

    static Tile create(
            final Optional<Piece> piece,
            final Position position) {
        Color color = (position.row() + position.column()) % 2 == 0 ? Color.WHITE : Color.BLACK;
        return piece != null ?
                OccupiedTile.create(piece, position, color) :
                EmptyTile.create(position, color);
    }

    enum Color {
        BLACK,
        WHITE
    }

    record OccupiedTile(
            Optional<Piece> piece,
            Position position,
            Tile.Color color) implements Tile {
        static Tile.OccupiedTile create(
                final Optional<Piece> piece,
                final Position position,
                final Tile.Color color) {
            return new Tile.OccupiedTile(piece, position, color);
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
                    .filter(position -> board.pieces().containsKey(position))
                    .collect(Collectors.toSet());
        }
    }

    record EmptyTile(
            Position position,
            Tile.Color color) implements Tile {

        @Override
        public Optional<Piece> piece() {
            return Optional.empty();
        }

        @Override
        public Set<Position> possibleMoves(
                final Board board) {
            return Set.of();
        }

        static Tile.EmptyTile create(
                final Position position,
                final Tile.Color color) {
            return new Tile.EmptyTile(position, color);
        }

        @Override
        public String toString() {
            return this.color().toString().charAt(0) + "  ";
        }
    }
}
