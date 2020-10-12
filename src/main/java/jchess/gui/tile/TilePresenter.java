package jchess.gui.tile;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jchess.core.Tile;

import java.net.URL;
import java.util.ResourceBundle;

public class TilePresenter implements Initializable {

    @FXML
    ImageView imageView;

    @FXML
    Rectangle rectangle;

    private final ObjectProperty<Tile> tileProperty = new SimpleObjectProperty<>();

    @Override
    public void initialize(
            final URL url,
            final ResourceBundle resourceBundle) {
        this.tileProperty.addListener(
                (observableValue, oldTile, newTile) -> {
                    fillRectangle(newTile);
                    newTile.piece().ifPresent(piece -> this.imageView.setImage(new Image(piece.getImageName())));
                }
        );
    }

    private void fillRectangle(
            final Tile tile) {
        final var originalColor = tile.color() == Tile.Color.WHITE
                ? Color.LIGHTGREEN
                : Color.DIMGRAY;
        this.rectangle.setFill(originalColor);
    }

    public ObjectProperty<Tile> tilePropertyProperty() {
        return tileProperty;
    }

    @FXML
    private void onMousePressed(
            final MouseEvent mouseEvent) {
        final var tile = this.tileProperty.get();
        tile.piece().ifPresent(System.out::println);
    }

    @FXML
    private void onMouseReleased(
            final MouseEvent mouseEvent) {
    }
}
