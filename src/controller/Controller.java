package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import json.JsonWriter;
import json.Mapper;
import model.Users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    Label username, password, id;
    @FXML
    TextField userPost, passPost, userPut, passPut, userDelete, passDelete;

    public void initialize(){
//        username.setText("xd");
//        password.setText("xd");
//        id.setText("xd");
    }

    //post
    public void postButton() throws IOException {
        Users users = new Users();
        users.setUsername(userPost.getText());
        users.setPassword(passPost.getText());

        URL url = new URL("http://localhost:8080/api/v1/demoDB");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        jsonWriterMethod(users, connection);

        connection.connect();
    }

    public void getButton() throws IOException {
        URL url = new URL("http://localhost:8080/api/v1/demoDB");

        Mapper mapper = new Mapper();
        List<Users> usersList = mapper.readJsonList(url);
        System.out.println(usersList);

        Users users = usersList.get(0);
        username.setText(users.getUsername());
        password.setText(users.getPassword());
        id.setText(Integer.toString(users.getIdUser()));
    }

   public void putButton() throws IOException{
       URL url = new URL("http://localhost:8080/api/v1/demoDB/1");
       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
       connection.setRequestMethod("PUT");
       connection.setRequestProperty("Content-Type", "application/json; utf-8");
       connection.setRequestProperty("Accept", "application/json");
       connection.setDoOutput(true);

       Users users = new Users();
       users.setUsername(userPut.getText());
       users.setPassword(passPut.getText());

       jsonWriterMethod(users, connection);

       connection.connect();
    }

    public void deleteButton()throws IOException{
        URL url = new URL("http://localhost:8080/api/v1/demoDB/2");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoInput(true);
        int responseCode = connection.getResponseCode();
        connection.connect();

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
            System.out.println(response.toString());
        }
    }
}
