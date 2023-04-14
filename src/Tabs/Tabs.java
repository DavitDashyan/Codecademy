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
    @Override
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

    @SuppressWarnings({"rawtypes", "unchecked"})
    private BorderPane createStudentsContent() {
        BorderPane studentsPane = new BorderPane();

        TableView<Student> studentsTable = new TableView<>();
        TableColumn<Student, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        TableColumn<Student, String> nameColumn = new TableColumn<>("Naam");
        TableColumn<Student, String> birthDateColumn = new TableColumn<>("Geboortedatum");
        TableColumn<Student, String> genderColumn = new TableColumn<>("Geslacht");
        TableColumn<Student, String> cityColumn = new TableColumn<>("Stad");
        TableColumn<Student, String> countryColumn = new TableColumn<>("Land");

        TableColumn<Student, Integer>[] columns = new TableColumn[]{
            idColumn, emailColumn, nameColumn, birthDateColumn, genderColumn, cityColumn, countryColumn
        };

        studentsTable.getColumns().addAll(columns);

        HBox buttonsBox = new HBox();
        Button addButton = new Button("Toevoegen");
        Button updateButton = new Button("Bijwerken");
        Button deleteButton = new Button("Verwijderen");

        buttonsBox.getChildren().addAll(addButton, updateButton, deleteButton);
        buttonsBox.setSpacing(10);

        studentsPane.setCenter(studentsTable);
        studentsPane.setBottom(buttonsBox);

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
