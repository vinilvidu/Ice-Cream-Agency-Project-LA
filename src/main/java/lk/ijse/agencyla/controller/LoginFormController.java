package lk.ijse.agencyla.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {
    @FXML
    public TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    public AnchorPane rootNode;


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

            String userName = txtUserName.getText();
            String Password = txtPassword.getText();

            try {
                checkCredential(userName, Password);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "OOPS! something went wrong").show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

    }



    private void checkCredential(String userName, String Password) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT userName, Password FROM admin WHERE userName = ?";

        Connection connection = lk.ijse.agencyla.db.DBConnection.getDbConnection().
                getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userName);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String dbPw = resultSet.getString(2);

            if(dbPw.equals(Password )) {
                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "user id not found!").show();
        }
    }

    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");

    }

    public void linkRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/registration_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Registration Form");

        stage.show();
    }


}
