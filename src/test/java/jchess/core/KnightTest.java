package jchess.core;


import jchess.core.Alliance;
import jchess.core.Piece;
import jchess.core.Position;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class KnightTest implements WithAssertions {

    @Test
    void test() throws Exception {
        // GIVEN
        var knight = Piece.Knight.create(Alliance.BLACK);

        // WHEN
        var positions = knight.possibleMoves();

        // THEN
        assertThat(positions).contains(Position.of(-2,1));
    }
}