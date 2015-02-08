/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telcell;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author default
 */
public class Global {

    private static Global instance = null;
    private Scene scene;
    private final Stage stage;
    private Locale locale;

    private Global(Stage stage) {
        this.stage = stage;
        this.locale = new Locale("en", "US");
        try {
            //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.scene = new Scene(loadFxml("FXMLHome"), 1920, 1050);
            this.loadStyles();
            this.stage.setScene(this.scene);
            this.stage.setFullScreen(true);
            this.stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadStyles() {
        File f = new File("web/css/home.css");
        this.scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        f = new File("web/css/beeline.css");
        this.scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        f = new File("web/css/header.css");
        this.scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
    }

    public static Global getInstance(Stage stage) {
        if (instance == null) {
            instance = new Global(stage);
        }
        return instance;
    }

    public static Global getInstance() {
        return instance;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene stage) {
        this.scene = stage;
    }

    public void setLocale(Locale l) {
        this.locale = l;
    }

    private Parent loadFxml(String FxmlFileNameWithoutExtention) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("telcell/translations/lang", this.locale);
        return FXMLLoader.load(getClass().getResource("templates/" + FxmlFileNameWithoutExtention + ".fxml"), bundle);
    }

    public void showHome() {
        try {
            this.scene.setRoot(loadFxml("FXMLHome"));
        } catch (IOException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showBeeline() {
        try {
            this.scene.setRoot(loadFxml("FXMLBeeline"));
        } catch (IOException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
