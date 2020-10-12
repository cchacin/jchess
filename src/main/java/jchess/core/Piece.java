package jchess.core;

import java.util.HashSet;
import java.util.Set;

public interface Piece {
    Player player();

    Set<Position> possibleMoves();

    default String getImageName() {
        return "/images/" + player().alliance().name() + "_" + getClass().getSimpleName().toUpperCase() + ".png";
    }

    record Knight(Player player) implements Piece {
        static final Set<Position> POSSIBLE_MOVES = Set.of(
                Position.of(2, 1),
                Position.of(2, -1),
                Position.of(-2, 1),
                Position.of(-2, -1),
                Position.of(1, 2),
                Position.of(1, -2),
                Position.of(-1, 2),
                Position.of(-1, -2)
        );

        static Knight create(
                final Player player) {
            return new Knight(player);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }

    record King(Player player) implements Piece {

        static final Set<Position> POSSIBLE_MOVES = Set.of(
                Position.of(1, 0),
                Position.of(1, 1),
                Position.of(1, -1),
                Position.of(0, 1),
                Position.of(0, -1),
                Position.of(-1, 0),
                Position.of(-1, 1),
                Position.of(-1, -1)
        );

        static King create(
                final Player player) {
            return new King(player);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }

    record Queen(Player player) implements Piece {

        static Queen create(
                final Player player) {
            return new Queen(player);
        }

        @Override
        public Set<Position> possibleMoves() {
            var positions = new HashSet<Position>();
            positions.addAll(Bishop.POSSIBLE_MOVES);
            positions.addAll(Rook.POSSIBLE_MOVES);
            return positions;
        }
    }

    record Rook(Player player) implements Piece {

        static final Set<Position> POSSIBLE_MOVES = Set.of(
                Position.of(0, 1),
                Position.of(0, 2),
                Position.of(0, 3),
                Position.of(0, 4),
                Position.of(0, 5),
                Position.of(0, 6),
                Position.of(0, 7),
                Position.of(0, -1),
                Position.of(0, -2),
                Position.of(0, -3),
                Position.of(0, -4),
                Position.of(0, -5),
                Position.of(0, -6),
                Position.of(0, -7),
                Position.of(1, 0),
                Position.of(2, 0),
                Position.of(3, 0),
                Position.of(4, 0),
                Position.of(5, 0),
                Position.of(6, 0),
                Position.of(7, 0),
                Position.of(-1, 0),
                Position.of(-2, 0),
                Position.of(-3, 0),
                Position.of(-4, 0),
                Position.of(-5, 0),
                Position.of(-6, 0),
                Position.of(-7, 0)
        );

        static Rook create(
                final Player player) {
            return new Rook(player);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }

    record Bishop(Player player) implements Piece {

        static final Set<Position> POSSIBLE_MOVES = Set.of(
                Position.of(1, 1),
                Position.of(2, 2),
                Position.of(3, 3),
                Position.of(4, 4),
                Position.of(5, 5),
                Position.of(6, 6),
                Position.of(7, 7),
                Position.of(-1, -1),
                Position.of(-2, -2),
                Position.of(-3, -3),
                Position.of(-4, -4),
                Position.of(-5, -5),
                Position.of(-6, -6),
                Position.of(-7, -7)
        );

        static Bishop create(
                final Player player) {
            return new Bishop(player);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }

    record Pawn(Player player) implements Piece {

        static final Set<Position> POSSIBLE_MOVES = Set.of(
                Position.of(1, 1),
                Position.of(1, -1),
                Position.of(1, 0),
                Position.of(2, 0)
        );

        static Pawn create(
                final Player player) {
            return new Pawn(player);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }

}
