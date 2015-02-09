/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telcell.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import telcell.Global;
import telcell.interfaces.Showable;
import telcell.managers.SoundManager;

/**
 *
 * @author default
 */
public class FXMLHomeController implements Initializable, Showable {

    @FXML
    private Button button;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Global global = Global.getInstance();
        global.showBeeline();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @Override
    public void onShow() {
        SoundManager.startOmxPlayer("audio/test.mp3");
    }

}
