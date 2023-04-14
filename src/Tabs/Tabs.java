/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabs;

/**
 *
 * @author dashy
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Tabs extends Application {

    private Scene mainScene;
    private Stage primaryStage;
    private BorderPane mainPane;
    private TabPane tabPane;

    // Methode voor het starten van de applicatie
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        mainPane = new BorderPane();
        tabPane = new TabPane();

        // Toevoegen van tabbladen
        Tab studentsTab = new Tab("Cursisten");
        Tab coursesTab = new Tab("Cursussen");
        Tab registrationsTab = new Tab("Inschrijvingen");
        Tab certificatesTab = new Tab("Certificaten");

        // Toevoegen van de inhoud van elke tabblad
        studentsTab.setContent(createStudentsContent());
        coursesTab.setContent(createCoursesContent());
        registrationsTab.setContent(createRegistrationsContent());
        certificatesTab.setContent(createCertificatesContent());

        // Toevoegen van de tabbladen aan de tabPane
        tabPane.getTabs().addAll(studentsTab, coursesTab, registrationsTab, certificatesTab);

        // Toevoegen van de tabPane aan het midden van het hoofdpaneel
        mainPane.setCenter(tabPane);

        // Creëren van de hoofdscene
        mainScene = new Scene(mainPane, 800, 600);

        // Instellen van de titel en de scene voor het primaryStage
        primaryStage.setTitle("Studentregistratie Applicatie");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    // Methode voor het maken van het scherm voor het beheren van cursisten
    private BorderPane createStudentsContent() {
        BorderPane studentsPane = new BorderPane();
        // Inhoud van het scherm voor het beheren van cursisten
        return studentsPane;
    }

    // Methode voor het maken van het scherm voor het beheren van cursussen
    private BorderPane createCoursesContent() {
        BorderPane coursesPane = new BorderPane();
        // Inhoud van het scherm voor het beheren van cursussen
        return coursesPane;
    }

    // Methode voor het maken van het scherm voor het beheren van inschrijvingen
    private BorderPane createRegistrationsContent() {
        BorderPane registrationsPane = new BorderPane();
        // Inhoud van het scherm voor het beheren van inschrijvingen
        return registrationsPane;
    }

    // Methode voor het maken van het scherm voor het beheren van certificaten
    private BorderPane createCertificatesContent() {
        BorderPane certificatesPane = new BorderPane();
        // Inhoud van het scherm voor het beheren van certificaten
        return certificatesPane;
    }

    // Methode voor het afsluiten van de applicatie
    public void stop() {
        // Afsluiten van de applicatie
    }

    // Start de applicatie
    public static void main(String[] args) {
        launch(args);
    }
}

