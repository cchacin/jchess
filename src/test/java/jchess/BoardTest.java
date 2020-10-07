package jchess;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class BoardTest implements WithAssertions {

    @Test
    void test() throws Exception {
        // GIVEN
        var board = Board.create();

        // WHEN
        var tile = board.getTile(Position.of(0, 0));

        // THEN
        assertThat(tile.piece()).contains(Piece.Rook.create(Alliance.WHITE));
    }
}