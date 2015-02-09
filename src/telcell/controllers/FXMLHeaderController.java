/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telcell.controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import telcell.Global;
import telcell.interfaces.Showable;

/**
 *
 * @author default
 */
public class FXMLHeaderController implements Initializable, Showable {

    @FXML
    private void setLangToEn(ActionEvent event) {
        Global global = Global.getInstance();
        global.setLocale(new Locale("en", "US"));
    }

    @FXML
    private void setLangToHy(ActionEvent event) {
        Global global = Global.getInstance();
        global.setLocale(new Locale("hy", "AM"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void onShow() {

    }

}
