/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telcell;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import telcell.controllers.FXMLHomeController;
import telcell.interfaces.Showable;
import telcell.managers.SoundManager;
import telcell.util.Pair;

/**
 *
 * @author default
 */
public class Global {

    private static Global instance;
    private Scene scene;
    private final Stage stage;
    private Locale locale;
    private final int screenWidth;
    private final int screenHeight;
    private String currentPageName;

    private Global(Stage stage) {
        screenWidth = 1920;
        screenHeight = 1080;
        this.stage = stage;
        this.locale = new Locale("en", "US");

    }

    public void start() {
        this.scene = new Scene(loadFxml("FXMLHome").getFirst(), screenWidth, screenHeight);
        this.loadStyles();
        this.stage.setScene(this.scene);
        this.stage.setFullScreen(true);
        this.stage.show();
        showHome();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    private void loadStyles() {
        String root = getJarDir();
        File f = new File(root + "/web/css/home.css");
        this.scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        f = new File(root + "/web/css/beeline.css");
        this.scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        f = new File(root + "/web/css/header.css");
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
        reloadPage();

    }

    private Pair<Parent, Showable> loadFxml(String FxmlFileNameWithoutExtention) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("telcell/translations/lang", this.locale);
            this.currentPageName = FxmlFileNameWithoutExtention;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("templates/" + this.currentPageName + ".fxml"), bundle);
            Parent root = loader.load();
            Showable controller = loader.getController();
            return new Pair(root, controller);
        } catch (IOException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setSceneRoot(Pair<Parent, Showable> pair) {
        if (pair != null) {
            SoundManager.stopOmxPlayer();
            this.scene.setRoot(pair.getFirst());
            pair.getSecond().onShow();
        }
    }

    public void reloadPage() {
        setSceneRoot(loadFxml(currentPageName));
    }

    public void showHome() {

        setSceneRoot(loadFxml("FXMLHome"));

    }

    public void showBeeline() {

        setSceneRoot(loadFxml("FXMLBeeline"));

    }

    public String getJarDir() {
        File f = new File(System.getProperty("java.class.path"));
        File dir = f.getAbsoluteFile().getParentFile();
        String toString = dir.toString();
        //toString = "C:/Users/default.User/Documents/Git/telcell_jfx";
        return toString;
    }

}
