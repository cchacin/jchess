package jchess.gui.board;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import jchess.gui.Game;
import jchess.core.Board;
import jchess.gui.tile.TilePresenter;
import jchess.gui.tile.TileView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class BoardPresenter implements Initializable {

    @FXML
    AnchorPane boardPane;

    private final ObjectProperty<Board> boardProperty = new SimpleObjectProperty<>();

    @Override
    public void initialize(
            final URL url,
            final ResourceBundle resourceBundle) {
        this.boardProperty.addListener((observable, oldBoard, newBoard) -> {
            if (newBoard != null) {
                final var tiles = newBoard.tiles();
                IntStream.range(0, 8).forEach(
                        row -> IntStream.range(0, 8).forEach(
                                column -> {
                                    final var tileView = new TileView();
                                    final var tilePresenter = (TilePresenter) tileView.getPresenter();
                                    tilePresenter.tileProperty().set(tiles[row][column]);
                                    this.boardPane.getChildren().add(tileView.getView());
                                }
                        )
                );
            }
        });
        this.boardProperty.set(Game.board.get());
    }

    public ObjectProperty<Board> boardProperty() {
        return boardProperty;
    }
}
