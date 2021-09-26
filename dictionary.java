package com.example.dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Dictionary extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane=createGridPane();
        addUIControls(gridPane);
        stage.setScene(new Scene(gridPane,800, 500));
        stage.setTitle("Dictionary");
        stage.show();

    }

    private void addUIControls(GridPane gridPane) {
        Label headerLabel=new Label("Dictionary");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20,0,20,0));

        Label wordLabel = new Label("Enter Word: ");
        gridPane.add(wordLabel,0,1);

        TextField wordField =new TextField();
        wordField.setPrefHeight(40);
        gridPane.add(wordField,1,1);

        Label defLabel = new Label("");
        defLabel.setWrapText(true);
        gridPane.add(defLabel, 1,3);



        wordField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (wordField.getText().isEmpty()) {
                    defLabel.setText("None");
                }
                else {
                    defLabel.setText(readJSON().get(wordField.getText().toUpperCase()));
                }
            }
        });
    }
    private GridPane createGridPane(){
        GridPane gridPane=new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40,40,40,40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints=new ColumnConstraints(100,100,Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstraints=new ColumnConstraints(200,200,Double.MAX_VALUE);
        columnTwoConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstraints);
        return gridPane;
    }

    private Map<String, String> readJSON() {
        Map<String, String> test= new HashMap<>();
        try {
            JSONParser parser =new JSONParser();
            Object obj = parser.parse(new FileReader("C:\\Users\\Harmony\\dictionary\\dict.json"));
            JSONObject jsonObject = (JSONObject) obj;
            //Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            //JsonArray jsonArray = (JsonArray) objects.get(0);


            for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                test.put(key, (String) jsonObject.get(key));
            }




        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Caught!");
        }

        return test;
    }

    public static void main(String[] args) {
        launch();
    }
}