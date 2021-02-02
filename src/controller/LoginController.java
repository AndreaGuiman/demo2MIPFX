package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import json.JsonWriter;
import model.Users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button buttonLogin;

    String response;

    public void loginAction() throws IOException {
        Users users = new Users();
        users.setUsername(username.getText());
        users.setPassword(password.getText());

        URL url = new URL("http://localhost:8080/api/v1/login");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        jsonWriterMethod(users, connection);

        connection.connect();

        if(response.equals("found")){
            buttonLogin.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
            Stage loginSuccessful = new Stage();
            loginSuccessful.setTitle("Hello World");
            loginSuccessful.setScene(new Scene(root));
            loginSuccessful.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username or password is wrong, or the user is not in the database");
            alert.setTitle("Opss!");
            alert.setHeaderText(null);
            alert.show();
        }

//        String response = connection.getResponseMessage();
//        System.out.println(response);
    }

    public void jsonWriterMethod(Users users, HttpURLConnection connection) throws IOException {
        JsonWriter jsonWriter = new JsonWriter();
        String jsonInput = jsonWriter.writeJson(users);

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

//            this.response = response.toString();
//            System.out.println(this.response);
        }
    }
}
