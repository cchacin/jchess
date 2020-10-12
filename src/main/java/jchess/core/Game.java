package jchess.core;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public final class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final List<OnMoveListener> listeners;

    private static final AtomicReference<Player> CURRENT_PLAYER = new AtomicReference<>();

    public Game() {
        player1 = new Player(Alliance.WHITE, new HashMap<>(32), new ArrayList<>(64));
        player2 = new Player(Alliance.BLACK, new HashMap<>(32), new ArrayList<>(64));
        CURRENT_PLAYER.set(player1);
        this.board = new Board(initialPieces(player1, player2));
        this.listeners = List.of(player1, player2, board);
    }

    public void move(
            final Player player,
            final Position origin,
            final Position destiny) {
        if (player.equals(player1)) {
            CURRENT_PLAYER.set(player2);
        } else {
            CURRENT_PLAYER.set(player1);
        }
        listeners.forEach(listener -> listener.onMove(player, origin, destiny));
    }

    public Player getCurrentPlayer() {
        return CURRENT_PLAYER.get();
    }

    public static Map<Position, Optional<Piece>> initialPieces(
            final Player player1,
            final Player player2) {
        final var map = new HashMap<Position, Optional<Piece>>(32);
        map.put(Position.of(0, 0), Optional.of(Piece.Rook.create(player1)));
        map.put(Position.of(0, 1), Optional.of(Piece.Knight.create(player1)));
        map.put(Position.of(0, 2), Optional.of(Piece.Bishop.create(player1)));
        map.put(Position.of(0, 3), Optional.of(Piece.Queen.create(player1)));
        map.put(Position.of(0, 4), Optional.of(Piece.King.create(player1)));
        map.put(Position.of(0, 5), Optional.of(Piece.Bishop.create(player1)));
        map.put(Position.of(0, 6), Optional.of(Piece.Knight.create(player1)));
        map.put(Position.of(0, 7), Optional.of(Piece.Rook.create(player1)));
        IntStream.range(0, 8).forEach(col -> map.put(Position.of(1, col), Optional.of(Piece.Pawn.create(player1))));

        IntStream.range(2, 6).forEach(row -> IntStream.range(0, 8).forEach(col -> map.put(Position.of(row, col), Optional.empty())));

        map.put(Position.of(7, 0), Optional.of(Piece.Rook.create(player2)));
        map.put(Position.of(7, 1), Optional.of(Piece.Knight.create(player2)));
        map.put(Position.of(7, 2), Optional.of(Piece.Bishop.create(player2)));
        map.put(Position.of(7, 3), Optional.of(Piece.Queen.create(player2)));
        map.put(Position.of(7, 4), Optional.of(Piece.King.create(player2)));
        map.put(Position.of(7, 5), Optional.of(Piece.Bishop.create(player2)));
        map.put(Position.of(7, 6), Optional.of(Piece.Knight.create(player2)));
        map.put(Position.of(7, 7), Optional.of(Piece.Rook.create(player2)));
        IntStream.range(0, 8).forEach(col -> map.put(Position.of(6, col), Optional.of(Piece.Pawn.create(player2))));
        return map;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
