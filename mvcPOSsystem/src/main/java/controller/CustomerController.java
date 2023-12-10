package controller;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerController {
    public AnchorPane customerPane;
    public JFXButton btnBack;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) customerPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Home.fxml"))));
        stage.show();
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/Blank_A4_2.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
