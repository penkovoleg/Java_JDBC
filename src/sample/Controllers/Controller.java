package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Shake;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button authSingInButton;

    @FXML
    private Button loginSingUpButton;

    @FXML
    void initialize(){
        authSingInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();//trim - для удаления лишних пробелов
            String loginPassword = password_field.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
            }
            else System.out.println("Login and password is empty");
        });

        loginSingUpButton.setOnAction(event ->{
            openNewScene("/sample/View/singUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword){
        DatabaseHandle dbHandle = new DatabaseHandle();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandle.getUser(user);
        int counter = 0;
        try{
            while (result.next()){
            counter++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        if(counter >= 1){
            authSingInButton.setOnAction(event ->{
                openNewScene("/sample/View/app.fxml");
            });
        }
        else{
            Shake userLoginAnimation = new Shake(login_field);
            Shake userPasswordAnimation = new Shake(password_field);
            Shake userButtonAnimation = new Shake(authSingInButton);
            userLoginAnimation.playAnim();
            userPasswordAnimation.playAnim();
            userButtonAnimation.playAnim();
        }
    }
    public void openNewScene(String window){
        loginSingUpButton.getScene().getWindow().hide();//скрываем окно
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));//адрес запускаемого окна
        try {
            loader.load();//загрузка
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();//путь к файлу
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));//указываем загружаемое окно
        stage.showAndWait();//отображение
    }
}
