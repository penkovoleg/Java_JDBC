package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField singUpName;

    @FXML
    private TextField singUpSurnname;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    private TextField singUpCountry;

    @FXML
    private Button singUpButton;

    @FXML
    private Button singUpButtonBack;

    @FXML
    private CheckBox singUpCheckBoxWoman;

    @FXML
    private CheckBox singUpCheckBoxMan;

    @FXML
    void initialize(){
        singUpButton.setOnAction(event -> {
            singUpNewUser();
        });
    }

    private void singUpNewUser() {
        DatabaseHandle dbHandler = new DatabaseHandle();
        String firstName = singUpName.getText();
        String lastName = singUpSurnname.getText();
        String userName = login_field.getText();
        String password = password_field.getText();
        String location = singUpCountry.getText();
        String gender = "";
        if(singUpCheckBoxMan.isSelected()) gender = "Мужской";
        else gender = "Женский";

        User user = new User(firstName, lastName, userName, password, location, gender);


        try {
            dbHandler.signUpUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
