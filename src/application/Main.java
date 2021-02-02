package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.JsonWriter;
import json.Mapper;
import model.Users;
import org.apache.log4j.BasicConfigurator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        BasicConfigurator.configure();

//        Users users = new Users();
//        users.setUsername("!");
//        users.setPassword("?");
//
//        JsonWriter jsonWriter = new JsonWriter();
//        jsonWriter.writeJson(users);
//        Mapper mapper = new Mapper();
//        mapper.readJson();
//
//        Users users1 = new Users();
//        users1.setUsername("xd");
//        users1.setPassword("xd2");
//
//        jsonWriter.writeJson(users1);
//        mapper.readJson();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
