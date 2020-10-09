package jchess.gui;

import jchess.core.Board;

import java.util.concurrent.atomic.AtomicReference;

public class Game {
    public static final AtomicReference<Board> BOARD_REFERENCE =
            new AtomicReference<>(Board.create());
}
