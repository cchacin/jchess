package jchess;

public interface Move {
    Board board();

    Piece piece();

    int destinationCoordinate();

    record MajorMove(
            Board board,
            Piece piece,
            int destinationCoordinate) implements Move {

    }

    record AttackMove(
            Board board,
            Piece piece,
            int destinationCoordinate,
            Piece attackedPiece) implements Move {

    }
}
