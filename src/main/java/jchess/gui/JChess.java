package jchess.gui;

import com.airhacks.afterburner.injection.Injector;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jchess.gui.main.MainView;
import jchess.gui.tile.TileView;

public class JChess extends Application {

    public static void main(
            final String[] args) {
        launch(args);
    }

    @Override
    public void start(
            final Stage stage) {
        final var main = new MainView();
        final var scene = new Scene(main.getView(), 8 * 100, 8 * 100);

        stage.setTitle("JChess");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }
}
