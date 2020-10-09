package jchess.gui.board;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import jchess.core.Board;
import jchess.gui.Game;
import jchess.gui.tile.TilePresenter;
import jchess.gui.tile.TileView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class BoardPresenter implements Initializable {

    @FXML
    GridPane boardPane;

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
                                    tilePresenter.tilePropertyProperty().set(tiles[row][column]);
                                    this.boardPane.add(tileView.getView(), column, row);
                                }
                        )
                );
            }
        });
        this.boardProperty.set(Game.BOARD_REFERENCE.get());
    }

    public ObjectProperty<Board> boardProperty() {
        return boardProperty;
    }
}
