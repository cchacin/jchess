package jchess.core;

import java.util.Optional;

public record MoveEvent(
        Player player,
        Optional<Player> opponent,
        Tile from,
        Tile to) {
}
