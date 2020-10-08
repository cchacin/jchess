package jchess.core;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface Piece {
    Alliance alliance();

    Set<Position> possibleMoves();

    record Knight(Alliance alliance) implements Piece {
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
                final Alliance alliance) {
            return new Knight(alliance);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }

    record King(Alliance alliance) implements Piece {

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
                final Alliance alliance) {
            return new King(alliance);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }

    record Queen(Alliance alliance) implements Piece {

        static Queen create(
                final Alliance alliance) {
            return new Queen(alliance);
        }

        @Override
        public Set<Position> possibleMoves() {
            var positions = new HashSet<Position>();
            positions.addAll(Bishop.POSSIBLE_MOVES);
            positions.addAll(Rook.POSSIBLE_MOVES);
            return positions;
        }
    }

    record Rook(Alliance alliance) implements Piece {

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
                final Alliance alliance) {
            return new Rook(alliance);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }

    record Bishop(Alliance alliance) implements Piece {

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
                final Alliance alliance) {
            return new Bishop(alliance);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }

    record Pawn(Alliance alliance) implements Piece {

        static final Set<Position> POSSIBLE_MOVES = Set.of(
                Position.of(1, 1),
                Position.of(1, -1),
                Position.of(1, 0),
                Position.of(2, 0)
        );

        static Pawn create(
                final Alliance alliance) {
            return new Pawn(alliance);
        }

        @Override
        public Set<Position> possibleMoves() {
            return Set.copyOf(POSSIBLE_MOVES);
        }
    }
}
