package jchess;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static jchess.Tile.Color;

public record Board(
        Tile[][] tiles) {

    public static Board create(
            final Tile[][] tiles) {
        return new Board(tiles);
    }

    public static Board create() {
        final var tiles = new Tile[8][8];

        initPieces().forEach(
                (position, piece) -> tiles[position.row()][position.column()] = Tile.create(
                        piece,
                        position,
                        (position.row() + position.column()) % 2 == 0 ? Color.WHITE : Color.BLACK
                )
        );

        IntStream.range(2, 6).forEach(row -> /* Empty rows 2 to 6 */
                IntStream.range(0, 8).forEach(column -> /* Empty columns 0 to 8 */
                        tiles[row][column] = Tile.empty(
                                Position.of(row, column),
                                (row + column) % 2 == 0 ? Color.WHITE : Color.BLACK
                        )
                )
        );

        return Board.create(tiles);
    }

    static Map<Position, Piece> initPieces() {
        var map = new HashMap<Position, Piece>(32);
        map.put(Position.of(0, 0), Piece.Rook.create(Alliance.WHITE));
        map.put(Position.of(0, 1), Piece.Knight.create(Alliance.WHITE));
        map.put(Position.of(0, 2), Piece.Bishop.create(Alliance.WHITE));
        map.put(Position.of(0, 3), Piece.Queen.create(Alliance.WHITE));
        map.put(Position.of(0, 4), Piece.King.create(Alliance.WHITE));
        map.put(Position.of(0, 5), Piece.Bishop.create(Alliance.WHITE));
        map.put(Position.of(0, 6), Piece.Knight.create(Alliance.WHITE));
        map.put(Position.of(0, 7), Piece.Rook.create(Alliance.WHITE));
        IntStream.range(0, 8).forEach(col -> map.put(Position.of(1, col), Piece.Pawn.create(Alliance.WHITE)));

        map.put(Position.of(7, 0), Piece.Rook.create(Alliance.BLACK));
        map.put(Position.of(7, 1), Piece.Knight.create(Alliance.BLACK));
        map.put(Position.of(7, 2), Piece.Bishop.create(Alliance.BLACK));
        map.put(Position.of(7, 3), Piece.Queen.create(Alliance.BLACK));
        map.put(Position.of(7, 4), Piece.King.create(Alliance.BLACK));
        map.put(Position.of(7, 5), Piece.Bishop.create(Alliance.BLACK));
        map.put(Position.of(7, 6), Piece.Knight.create(Alliance.BLACK));
        map.put(Position.of(7, 7), Piece.Rook.create(Alliance.BLACK));
        IntStream.range(0, 8).forEach(col -> map.put(Position.of(6, col), Piece.Pawn.create(Alliance.BLACK)));
        return map;
    }

    public Tile getTile(
            final Position position) {
        return tiles[position.row()][position.column()];
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder(150);
        for (var row = 0; row < 8; row++) {
            sb.append('|');
            for (var column = 0; column < 8; column++) {
                sb.append(getTile(Position.of(row, column))).append('|');
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
