package jchess.gui.board;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import jchess.core.Position;
import jchess.core.Tile;
import jchess.gui.tile.TilePresenter;
import jchess.gui.tile.TileView;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardPresenter implements Initializable {

    @FXML
    GridPane boardPane;

    private final ObservableMap<Position, Tile> tilesObservable = FXCollections.observableHashMap();

    @Override
    public void initialize(
            final URL url,
            final ResourceBundle resourceBundle) {

        this.tilesObservable.addListener((MapChangeListener<Position, Tile>) change -> {
            if (change.wasAdded()) {
                final var tile = change.getValueAdded();
                final var tileView = new TileView();
                final var tilePresenter = (TilePresenter) tileView.getPresenter();
                tilePresenter.tilePropertyProperty().set(tile);
                boardPane.add(tileView.getView(), tile.position().column(), tile.position().row());
            }
        });
    }

    public ObservableMap<Position, Tile> getTilesObservable() {
        return tilesObservable;
    }
}
