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
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        Users users = new Users();
        users.setUsername("!");
        users.setPassword("?");
        BasicConfigurator.configure();
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.writeJson(users);
        Mapper mapper = new Mapper();
        mapper.readJson();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
