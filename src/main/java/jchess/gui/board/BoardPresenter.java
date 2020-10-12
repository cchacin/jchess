package jchess.gui.board;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import jchess.core.Board;
import jchess.core.Tile;
import jchess.gui.tile.TilePresenter;
import jchess.gui.tile.TileView;

import java.net.URL;
import java.util.ResourceBundle;

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
                newBoard.pieces().forEach((position, piece) -> {
                    final var tileView = new TileView();
                    final var tilePresenter = (TilePresenter) tileView.getPresenter();
                    tilePresenter.tilePropertyProperty().set(Tile.create(piece, position));
                    this.boardPane.add(tileView.getView(), position.column(), position.row());
                });
            }
        });
    }

    public ObjectProperty<Board> boardProperty() {
        return boardProperty;
    }
}
