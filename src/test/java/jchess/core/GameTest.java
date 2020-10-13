package jchess.core;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class GameTest implements WithAssertions {

    @Test
    void test() throws Exception {
        // Given
        final var pos11 = new Position(1, 1);
        final var pos12 = new Position(1, 2);
        final var game = new Game();

        // Then
        game.move(game.getPlayer1(), Optional.of(game.getPlayer2()), game.getTiles().get(pos11), game.getTiles().get(pos12));

//        assertThat(game.getBoard().pieces()).isEqualTo(Game.initialPieces(game.getPlayer1(), game.getPlayer2()));

        // When
//        assertThat(game.getPlayer1().deadPieces()).hasSize(1);
//        assertThat(game.getPlayer2().alivePieces()).isEmpty();
//        assertThat(game.getPlayer2().alivePieces()).isEmpty();
//        assertThat(game.getCurrentPlayer()).isEqualTo(game.getPlayer2());
    }

}
