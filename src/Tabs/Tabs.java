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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Tabs extends Application {

    private List<Student> studentsList = new ArrayList<>();

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
//probeerde bij elke tab de knoppen te zetten maar lukt niet echt 
//        HBox buttonsBox = new HBox();
//        Button addButton = new Button("Toevoegen");
//        Button updateButton = new Button("Bijwerken");
//        Button deleteButton = new Button("Verwijderen");
//        buttonsBox.getChildren().addAll(addButton, updateButton, deleteButton);
        Tab coursesTab = new Tab("Cursussen");
        Tab registrationsTab = new Tab("Inschrijvingen");
        Tab certificatesTab = new Tab("Certificaten");

        //hard coded erin want er zijn maar dire dingen in de databse
        //dus het maakt niet uit waar het vandaan komt want is toch altijd zelfde gegevens
        //kan later aangepast worden zo nodig 
        Tab topdrieWeb = new Tab("Top 3 Webcasts");
        VBox tab1Content = new VBox();
        tab1Content.setSpacing(10); // voeg tussenruimte toe tussen de labels
        Label label1 = new Label("Title: Introduction to Machine Learning\n"
                + "Description: In this webcast, we provide an overview of machine learning and its applications.\n"
                + "Name speaker: John Smith\n"
                + "Organization: Codecademy\n"
                + "Duration: 45\n"
                + "URL: 'https://www.youtube.com/watch?v=5DknTFbcGVM'");
        label1.setPadding(new Insets(10));
        ; // voeg padding toe rondom de tekst
        Label label2 = new Label("Title: JavaScript for Beginners\n"
                + "Description: In this webcast, we cover the basics of JavaScript programming language.\n"
                + "Name speaker: Jane Doe\n"
                + "Organization: Codecademy\n"
                + "Duration: 30\n"
                + "URL: 'https://www.youtube.com/watch?v=Qqx_wzMmFeA'");
        label2.setPadding(new Insets(10));

        Label label3 = new Label("Title: Data Visualization with Python\n"
                + "Description: In this webcast, we explore data visualization techniques using Python programming language.\n"
                + "Name speaker: Bob Johnson\n"
                + "Organization: Codecademy\n"
                + "Duration: 75\n"
                + "URL: 'https://www.youtube.com/watch?v=uUOgQ2lJ6_I'");
        label3.setPadding(new Insets(10));

        tab1Content.getChildren().addAll(label1, label2, label3);
        topdrieWeb.setContent(tab1Content);

//        HBox buttonsBox = new HBox();
//        Button addButtonA = new Button("Toevoegen");
//        Button updateButtonA = new Button("Bijwerken");
//        Button deleteButtonA = new Button("Verwijderen");
//        buttonsBox.getChildren().addAll(addButtonA, updateButtonA, deleteButtonA);
//        //  studentsPane.setBottom(buttonsBox);
        // Tweede tab
        Tab topdrieCert = new Tab("Top 3 certificaten");
        VBox tab2Content = new VBox();
        tab1Content.setSpacing(10); // voeg tussenruimte toe tussen de labels
        Label label7 = new Label("De top 3 van cursussen met meest uitgegeven certificaten: ");
        label7.setPadding(new Insets(10));
        Label label4 = new Label("Certificaat nummer: 101");
        label4.setPadding(new Insets(10));
        ; // voeg padding toe rondom de tekst
        Label label5 = new Label("Certificaat nummer: 102");
        label5.setPadding(new Insets(10));

        Label label6 = new Label("Certificaat nummer: 103");
        label6.setPadding(new Insets(10));

        tab2Content.getChildren().addAll(label7, label4, label5, label6);
        topdrieCert.setContent(tab2Content);

        // Toevoegen van de inhoud van elke tabblad
        studentsTab.setContent(createStudentsContent());
        coursesTab.setContent(createCoursesContent());
        registrationsTab.setContent(createRegistrationsContent());
        certificatesTab.setContent(createCertificatesContent());

        // Toevoegen van de tabbladen aan de tabPane,heb de top 3 toegevoegd
        tabPane.getTabs().addAll(studentsTab, coursesTab, registrationsTab, certificatesTab, topdrieWeb, topdrieCert);

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
        // Define the columns of the table
        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Naam");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        TableColumn<Student, String> birthDateColumn = new TableColumn<>("Geboortedatum");
        birthDateColumn.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            LocalDate birthDate = cellData.getValue().getBirthDate();
            if (birthDate != null) {
                String formattedDate = birthDate.format(formatter);
                property.setValue(formattedDate);
            } else {
                property.setValue("");
            }
            return property;
        });

        TableColumn<Student, String> genderColumn = new TableColumn<>("Geslacht");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Student, String> cityColumn = new TableColumn<>("Stad");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Student, String> countryColumn = new TableColumn<>("Land");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

        // Add the columns to the table
        studentsTable.getColumns().addAll(emailColumn, nameColumn, birthDateColumn, genderColumn, cityColumn, countryColumn);

        studentsPane.setCenter(studentsTable);

        // Define selectedStudent variable
        Student selectedStudent = null;

        HBox buttonsBox = new HBox();
        Button addButton = new Button("Toevoegen");
        Button updateButton = new Button("Bijwerken");
        Button deleteButton = new Button("Verwijderen");
        buttonsBox.getChildren().addAll(addButton, updateButton, deleteButton);
        studentsPane.setBottom(buttonsBox);

        // Add event listener for adding a student
        addButton.setOnAction(e -> {
            // Show a dialog to get the student information
            Dialog<Student> dialog = new Dialog<>();
            dialog.setTitle("Student toevoegen");
            dialog.setHeaderText("Voer de gegevens van de student in:");

            // Create the dialog content
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField emailField = new TextField();
            emailField.setPromptText("Email");
            TextField nameField = new TextField();
            nameField.setPromptText("Naam");
            DatePicker birthDateField = new DatePicker();
            birthDateField.setPromptText("Geboortedatum");
            ComboBox<String> genderField = new ComboBox<>();
            genderField.getItems().addAll("Mannelijk", "Vrouwelijk", "Anders");

            if (selectedStudent != null) {
                genderField.setValue(selectedStudent.getGender());
            }

            TextField cityField = new TextField();
            cityField.setPromptText("Stad");
            TextField countryField = new TextField();
            countryField.setPromptText("Land");

            grid.add(new Label("Email:"), 0, 0);
            grid.add(emailField, 1, 0);
            grid.add(new Label("Naam:"), 0, 1);
            grid.add(nameField, 1, 1);
            grid.add(new Label("Geboortedatum:"), 0, 2);
            grid.add(birthDateField, 1, 2);
            grid.add(new Label("Geslacht:"), 0, 3);
            grid.add(genderField, 1, 3);
            grid.add(new Label("Stad:"), 0, 4);
            grid.add(cityField, 1, 4);
            grid.add(new Label("Land:"), 0, 5);
            grid.add(countryField, 1, 5);

            dialog.getDialogPane().setContent(grid);

            // Add the OK and Cancel buttons to the dialog
            ButtonType okButtonType = new ButtonType("Toevoegen", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

            // Convert the dialog result to a student object when the OK button is clicked
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == okButtonType) {
                    String email = emailField.getText();
                    String name = nameField.getText();
                    LocalDate birthDate = birthDateField.getValue();
                    String gender = genderField.getValue(); // fixed typo
                    String city = cityField.getText();
                    String country = countryField.getText();
                    return new Student(email, name, birthDate, gender, city, country);
                }
                return null;
            });

            Optional<Student> result = dialog.showAndWait();
            result.ifPresent(student -> {
                // Add the new student to the list
                studentsList.add(student);
                // Add the new student to the table
                studentsTable.getItems().add(student);

                // Refresh the table view to display the new student
                studentsTable.refresh();

                // Print all students in the table
                studentsTable.getItems().forEach(System.out::println);

                // Print the new student
                System.out.println(student);

                // Scroll to the new row in the table
                studentsTable.scrollTo(student);
            });

        });

        updateButton.setOnAction(event -> {
            // Get the selected student from the table
            Student updateSelectedStudent = studentsTable.getSelectionModel().getSelectedItem();
            if (updateSelectedStudent == null) {
                // No student selected, show an error message
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Fout");
                alert.setHeaderText(null);
                alert.setContentText("Er is geen student geselecteerd.");
                alert.showAndWait();
                return;
            }
            // Show a dialog to update the student information
            Dialog<Student> dialog = new Dialog<>();
            dialog.setTitle("Student bijwerken");
            dialog.setHeaderText("Pas de gegevens van de student aan:");

            // Create the dialog content
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField emailField = new TextField(updateSelectedStudent.getEmail());
            emailField.setPromptText("Email");
            TextField nameField = new TextField(updateSelectedStudent.getName());
            nameField.setPromptText("Naam");
            DatePicker birthDateField = new DatePicker(updateSelectedStudent.getBirthDate());
            birthDateField.setPromptText("Geboortedatum");
            ComboBox<String> genderField = new ComboBox<>();
            genderField.getItems().addAll("Mannelijk", "Vrouwelijk", "Anders");
            genderField.setValue(updateSelectedStudent.getGender());
            TextField cityField = new TextField(updateSelectedStudent.getCity());
            cityField.setPromptText("Stad");
            TextField countryField = new TextField(updateSelectedStudent.getCountry());
            countryField.setPromptText("Land");

            grid.add(new Label("Email:"), 0, 0);
            grid.add(emailField, 1, 0);
            grid.add(new Label("Naam:"), 0, 1);
            grid.add(nameField, 1, 1);
            grid.add(new Label("Geboortedatum:"), 0, 2);
            grid.add(birthDateField, 1, 2);
            grid.add(new Label("Geslacht:"), 0, 3);
            grid.add(genderField, 1, 3);
            grid.add(new Label("Stad:"), 0, 4);
            grid.add(cityField, 1, 4);
            grid.add(new Label("Land:"), 0, 5);
            grid.add(countryField, 1, 5);

            dialog.getDialogPane().setContent(grid);

            // Add the OK and Cancel buttons to the dialog
            ButtonType okButtonType = new ButtonType("Bijwerken", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

            // Convert the dialog result to a student object when the OK button is clicked
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == okButtonType) {
                    String email = emailField.getText();
                    String name = nameField.getText();
                    LocalDate birthDate = birthDateField.getValue();
                    String gender = genderField.getValue();
                    String city = cityField.getText();
                    String country = countryField.getText();
                    return new Student(email, name, birthDate, gender, city, country);
                }
                return null;
            });

            Optional<Student> result = dialog.showAndWait();

            if (result.isPresent()) {
                // Get the updated student information from the dialog result
                Student updatedStudent = result.get();
                // Update the selected student in the table with the new information
                updateSelectedStudent.setEmail(updatedStudent.getEmail());
                updateSelectedStudent.setName(updatedStudent.getName());
                updateSelectedStudent.setBirthDate(updatedStudent.getBirthDate());
                updateSelectedStudent.setGender(updatedStudent.getGender());
                updateSelectedStudent.setCity(updatedStudent.getCity());
                updateSelectedStudent.setCountry(updatedStudent.getCountry());
                // Refresh the table to reflect the changes
                studentsTable.refresh();
            }

        });

        deleteButton.setOnAction(event -> {
            // Get the selected student from the table
            Student deleteSelectedStudent = studentsTable.getSelectionModel().getSelectedItem();
            if (deleteSelectedStudent == null) {
                // No student selected, show an error message
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Fout");
                alert.setHeaderText(null);
                alert.setContentText("Er is geen student geselecteerd.");
                alert.showAndWait();
                return;
            }
            // Show a confirmation dialog before deleting the student
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Bevestig verwijdering");
            alert.setHeaderText("Weet u zeker dat u de geselecteerde student wilt verwijderen?");
            alert.setContentText(deleteSelectedStudent.getName() + " zal worden verwijderd.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the student from the list and table
                studentsList.remove(selectedStudent);
                studentsTable.getItems().remove(selectedStudent);
                // Refresh the table to reflect the changes
                studentsTable.refresh();
            }
        });

        return studentsPane;
    }

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
    @Override
    public void stop() {

    }
}
