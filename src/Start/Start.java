/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

import Tabs.Tabs;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to Codecademy");

        // Create welcomeTitle
        Text welcomeTitle = new Text("Welcome");
        welcomeTitle.setFont(new Font(46));

        // Create startButton
        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            // Maak een instantie van LoginPage
            Tabs tabsPage = new Tabs();

            try {
                // Open de login pagina
                tabsPage.start(new Stage());

                // Sluit de huidige pagina
                primaryStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Create vBoxWelcome
        VBox vBoxWelcome = new VBox(10);
        vBoxWelcome.setAlignment(Pos.CENTER);
        vBoxWelcome.getChildren().addAll(welcomeTitle, startButton);

        // Create welcomeAnchor
        AnchorPane welcomeAnchor = new AnchorPane(vBoxWelcome);
        AnchorPane.setTopAnchor(vBoxWelcome, 0.0);
        AnchorPane.setBottomAnchor(vBoxWelcome, 0.0);
        AnchorPane.setLeftAnchor(vBoxWelcome, 0.0);
        AnchorPane.setRightAnchor(vBoxWelcome, 0.0);

        // Set the scene
        Scene scene = new Scene(welcomeAnchor, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
