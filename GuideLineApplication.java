//Jackson Fuller
//8/4/23
//Capstone Project - Nova Guides GuideLine Maker
//Takes user input of tour information then writes to a file based on the date, ready for further use of data.

package com.guidelineapp.capstone;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;


import java.io.*;
import java.time.LocalDate;
public class GuideLineApplication extends Application {

    String tourTypes[] =
            {"Jeep", "ATV", "Side by Side", "Fly-Fishing", "Snowmobile"};
    String tourTime[] =
            {"Morning", "Midday", "Afternoon"};
    String Guide[] =
            {"Jackson", "Noah", "Matt", "Jon", "Sam"};

    String pickupLocations[] =
            {"Vail", "Avon", "Beaver Creek", "Drive Out"};


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GuideLine"); //set title

        //logo image
        String path = "src\\main\\resources\\novalogo.jpg";
        FileInputStream input = new FileInputStream(path);
        Image i = new Image(input);
        ImageView iw = new ImageView(i);
        iw.setFitHeight(75);
        iw.setFitWidth(75);

        //main label
        Label mainLabel = new Label("Nova GuideLine");
        mainLabel.setFont(new Font("Arial", 20));

        //date
        Label dateLabel = new Label("Date: ");
        DatePicker datePicker = new DatePicker();

        //tour type
        Label typeLabel = new Label("Type: ");
        ComboBox typeBox = new ComboBox(FXCollections.observableArrayList(tourTypes));

        //time selector
        Label timeLabel = new Label("Tour Time: ");
        ComboBox timeBox = new ComboBox(FXCollections.observableArrayList(tourTime));

        //guide selector
        Label guideLabel = new Label("Guide: ");
        ComboBox guideBox = new ComboBox(FXCollections.observableArrayList(Guide));

        //pickup selector
        Label pickupsLabel = new Label("Pickups? ");
        ComboBox pickupsBox = new ComboBox(FXCollections.observableArrayList(pickupLocations));

        //button
        Button addBtn = new Button("Add To GuideLine");

        //handling button click event
        addBtn.setOnAction(event -> {
            Object guide = guideBox.getValue();
            Object tourTime = timeBox.getValue();
            LocalDate date = datePicker.getValue();
            Object tourType = typeBox.getValue();
            Object pickups = pickupsBox.getValue();

            String arrivalTime = " ";

            //setting arrival time based on tour
            if (tourTime == "Morning") {
                arrivalTime = "7:30am";
            } else if (tourTime == "Midday") {
                arrivalTime = "10:30";
            } else if (tourTime == "Afternoon") {
                arrivalTime = "1:00";
            }

            //creates and writes to file
            createFile(date);
            Object o = arrivalTime + ": " + guide + " guiding " + tourType + ". Pickups: " + pickups + "\n";
            WriteToFile(date, o);
        });

        //setting up grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        //positioning elements on the grid
        grid.add(iw, 2, 0);
        grid.add(mainLabel, 0, 0);
        grid.add(dateLabel, 0, 1);
        grid.add(datePicker, 1, 1);
        grid.add(typeLabel, 0, 2);
        grid.add(typeBox, 1, 2);
        grid.add(timeLabel, 0, 3);
        grid.add(timeBox, 1, 3);
        grid.add(guideLabel, 0, 4);
        grid.add(guideBox, 1, 4);
        grid.add(pickupsLabel, 0, 5);
        grid.add(pickupsBox, 1, 5);
        grid.add(addBtn, 0, 6);


        //setting scene
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //main
    public static void main(String[] args) {
        launch(args);
    }
    //write to fill function
    public void WriteToFile(LocalDate date, Object o) {
        try {
            FileWriter myWriter = new FileWriter(date + ".txt", true);
            String s = o.toString();
            myWriter.write(s);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //create file function
    public void createFile(LocalDate date) {
        try {
            File myObj = new File(date + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                WriteToFile(date, date + "\n");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}