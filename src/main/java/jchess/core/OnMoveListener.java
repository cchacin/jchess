package jchess.core;

public interface OnMoveListener {
    void onMove(Player player, Position origin, Position destiny);
}
