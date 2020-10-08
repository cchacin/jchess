package jchess.gui.tile;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jchess.core.Tile;

import java.net.URL;
import java.util.ResourceBundle;

public class TilePresenter implements Initializable {

    @FXML
    AnchorPane anchorPane;

    private final ObjectProperty<Tile> tileProperty = new SimpleObjectProperty<>();

    @Override
    public void initialize(
            final URL url,
            final ResourceBundle resourceBundle) {
        this.tileProperty.addListener((observableValue, oldTile, newTile) -> {
            if (newTile != null) {
                final var originalColor = newTile.color() == Tile.Color.WHITE ? Color.WHITE : Color.DIMGRAY;
                final var rectangle = new Rectangle(
                        newTile.position().column() * 100,
                        newTile.position().row() * 100,
                        100,
                        100
                );
                rectangle.setFill(originalColor);
                this.anchorPane.getChildren().add(rectangle);
                newTile.piece().ifPresent(piece -> {
                    final var image = new Image("/images/" + piece.alliance().name() + "_" + piece.getClass().getSimpleName().toUpperCase() + ".png");
                    final var imageView = new ImageView(image);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    imageView.setX(newTile.position().column() * 100);
                    imageView.setY(newTile.position().row() * 100);
                    this.anchorPane.getChildren().add(imageView);
                });
            }
        });
    }

    public ObjectProperty<Tile> tileProperty() {
        return tileProperty;
    }

    public void onMousePressed(
            final MouseEvent mouseEvent) {
//        final var tile = this.tileProperty.get();
//        tile.piece().ifPresent(piece -> {
//            piece.possibleMoves()
//                    .stream()
//                    .map(position -> position.plus(tile.position()))
//                    .filter(Optional::isPresent)
//                    .map(Optional::get)
//                    .filter(position -> this.boardProperty.get().tiles()[position.row()][position.column()].piece().isEmpty())
//                    .forEach(position -> this.boardProperty.get().tiles()[position.row()][position.column()].activate());
//        });
    }

    public void onMouseReleased(
            final MouseEvent mouseEvent) {
    }
}
