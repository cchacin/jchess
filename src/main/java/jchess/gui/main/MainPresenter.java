package jchess.gui.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import jchess.core.Game;
import jchess.gui.board.BoardPresenter;
import jchess.gui.board.BoardView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPresenter implements Initializable {

    @FXML
    AnchorPane main;

    BoardPresenter boardPresenter;

    @Override
    public void initialize(
            final URL url,
            final ResourceBundle resourceBundle) {
        final var boardView = new BoardView();
        this.boardPresenter = (BoardPresenter) boardView.getPresenter();
        final var game = new Game();
        this.boardPresenter.boardProperty().set(game.getBoard());
        main.getChildren().add(boardView.getView());
    }
}
