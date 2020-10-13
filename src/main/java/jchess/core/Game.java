package jchess.core;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public final class Game {
    private final Player player1;
    private final Player player2;
    private final Map<Position, Tile> tiles;
    private final List<OnMoveListener> listeners;

    private static final AtomicReference<Player> CURRENT_PLAYER = new AtomicReference<>();

    public Game() {
        player1 = new Player(Alliance.WHITE, new ArrayList<>(32), new ArrayList<>(32));
        player2 = new Player(Alliance.BLACK, new ArrayList<>(32), new ArrayList<>(32));
        CURRENT_PLAYER.set(player1);
        this.listeners = List.of(player1, player2);
        this.tiles = initialPieces(player1, player2);
    }

    public void move(
            final Player player1,
            final Optional<Player> player2,
            final Tile from,
            final Tile to) {
        listeners.forEach(listener -> listener.onMove(new MoveEvent(player1, player2, from, to)));
    }

    public Player getCurrentPlayer() {
        return CURRENT_PLAYER.get();
    }

    public static Map<Position, Tile> initialPieces(
            final Player player1,
            final Player player2) {
        final var map = new HashMap<Position, Tile>(32);
        map.put(Position.of(0, 0), Tile.create(Position.of(0, 0), Optional.of(Piece.Rook.create(player1))));
        map.put(Position.of(0, 1), Tile.create(Position.of(0, 1), Optional.of(Piece.Knight.create(player1))));
        map.put(Position.of(0, 2), Tile.create(Position.of(0, 2), Optional.of(Piece.Bishop.create(player1))));
        map.put(Position.of(0, 3), Tile.create(Position.of(0, 3), Optional.of(Piece.Queen.create(player1))));
        map.put(Position.of(0, 4), Tile.create(Position.of(0, 4), Optional.of(Piece.King.create(player1))));
        map.put(Position.of(0, 5), Tile.create(Position.of(0, 5), Optional.of(Piece.Bishop.create(player1))));
        map.put(Position.of(0, 6), Tile.create(Position.of(0, 6), Optional.of(Piece.Knight.create(player1))));
        map.put(Position.of(0, 7), Tile.create(Position.of(0, 7), Optional.of(Piece.Rook.create(player1))));
        IntStream.range(0, 8).forEach(col -> map.put(Position.of(1, col), Tile.create(Position.of(1, col), Optional.of(Piece.Pawn.create(player1)))));

        IntStream.range(2, 6).forEach(row -> IntStream.range(0, 8).forEach(col -> map.put(Position.of(row, col), Tile.create(Position.of(row, col), Optional.empty()))));

        map.put(Position.of(7, 0), Tile.create(Position.of(7, 0), Optional.of(Piece.Rook.create(player2))));
        map.put(Position.of(7, 1), Tile.create(Position.of(7, 1), Optional.of(Piece.Knight.create(player2))));
        map.put(Position.of(7, 2), Tile.create(Position.of(7, 2), Optional.of(Piece.Bishop.create(player2))));
        map.put(Position.of(7, 3), Tile.create(Position.of(7, 3), Optional.of(Piece.Queen.create(player2))));
        map.put(Position.of(7, 4), Tile.create(Position.of(7, 4), Optional.of(Piece.King.create(player2))));
        map.put(Position.of(7, 5), Tile.create(Position.of(7, 5), Optional.of(Piece.Bishop.create(player2))));
        map.put(Position.of(7, 6), Tile.create(Position.of(7, 6), Optional.of(Piece.Knight.create(player2))));
        map.put(Position.of(7, 7), Tile.create(Position.of(7, 7), Optional.of(Piece.Rook.create(player2))));
        IntStream.range(0, 8).forEach(col -> map.put(Position.of(6, col), Tile.create(Position.of(6, col), Optional.of(Piece.Pawn.create(player2)))));
        return map;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Map<Position, Tile> getTiles() {
        return tiles;
    }
}
