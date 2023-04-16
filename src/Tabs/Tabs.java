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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public class Tabs extends Application {

        private List<Student> studentsList = new ArrayList<>();
        private ObservableList<Webcast> webcastsList = FXCollections.observableArrayList();

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
            Tab topdrieWebTab = new Tab("Top 3 Webcasts");

            // Toevoegen van de inhoud van elke tabblad
            studentsTab.setContent(createStudentsContent());
            coursesTab.setContent(createCoursesContent());
            registrationsTab.setContent(createRegistrationsContent());
            certificatesTab.setContent(createCertificatesContent());
            topdrieWebTab.setContent(createTopThreeWebcasts());

            // Toevoegen van de tabbladen aan de tabPane
            tabPane.getTabs().addAll(studentsTab, coursesTab, registrationsTab, certificatesTab, topdrieWebTab);

            // Toevoegen van de tabPane aan het midden van het hoofdpaneel
            mainPane.setCenter(tabPane);

            // Creëren van de hoofdscene
            mainScene = new Scene(mainPane, 800, 600);

            // Instellen van de titel en de scene voor het primaryStage
            primaryStage.setTitle("Studentregistratie Applicatie");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        }

        private void showAlert(Alert.AlertType alertType, String title, String content) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
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

    private BorderPane createTopThreeWebcasts() {
        BorderPane createTopThreeWebcastsPane = new BorderPane();

        TableView<Webcast> webcastTable = new TableView<>();
        // Define the columns of the table
        TableColumn<Webcast, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Webcast, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Webcast, String> speakerColumn = new TableColumn<>("Speaker");
        speakerColumn.setCellValueFactory(new PropertyValueFactory<>("speakerName"));

        TableColumn<Webcast, String> urlColumn = new TableColumn<>("URL");
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));

        webcastTable.getColumns().setAll(
                Arrays.asList(
                        titleColumn,
                        descriptionColumn,
                        speakerColumn,
                        urlColumn
                )
        );

        createTopThreeWebcastsPane.setCenter(webcastTable);

        // Define selectedWebcast variable
        Webcast selectedWebcast = null;

        HBox buttonsBox = new HBox();
        Button addButton = new Button("Toevoegen");
        Button updateButton = new Button("Bijwerken");
        Button deleteButton = new Button("Verwijderen");
        buttonsBox.getChildren().addAll(addButton, updateButton, deleteButton);
        createTopThreeWebcastsPane.setBottom(buttonsBox);

        // Add event listener for adding a webcast
        addButton.setOnAction(e -> {
            // Show a dialog to get the webcast information
            Dialog<Webcast> dialog = new Dialog<>();
            dialog.setTitle("Webcast toevoegen");
            dialog.setHeaderText("Voer de gegevens van de webcast in:");

            // Create the dialog
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            TextField titleField = new TextField();
            titleField.setPromptText("Title");
            TextArea descriptionField = new TextArea();
            descriptionField.setPromptText("Description");
            TextField speakerNameField = new TextField();
            speakerNameField.setPromptText("Speaker Name");
            TextField organizationField = new TextField();
            organizationField.setPromptText("Organization");
            Spinner<Integer> durationField = new Spinner<>(0, Integer.MAX_VALUE, 0);
            durationField.setPromptText("Duration");
            TextField urlField = new TextField();
            urlField.setPromptText("URL");

            grid.add(new Label("Title:"), 0, 0);
            grid.add(titleField, 1, 0);
            grid.add(new Label("Description:"), 0, 1);
            grid.add(descriptionField, 1, 1);
            grid.add(new Label("Speaker Name:"), 0, 2);
            grid.add(speakerNameField, 1, 2);
            grid.add(new Label("Organization:"), 0, 3);
            grid.add(organizationField, 1, 3);
            grid.add(new Label("Duration (min):"), 0, 4);
            grid.add(durationField, 1, 4);
            grid.add(new Label("URL:"), 0, 5);
            grid.add(urlField, 1, 5);
            dialog.getDialogPane().setContent(grid);

// Add the OK and Cancel buttons to the dialog
            ButtonType addButtonType = new ButtonType("Toevoegen", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

// Add event listener for the Add button
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    String title = titleField.getText();
                    String description = descriptionField.getText();
                    String speakerName = speakerNameField.getText();
                    String organization = organizationField.getText();
                    int duration = durationField.getValue();
                    String url = urlField.getText();
                    Webcast newWebcast = new Webcast(title, description, speakerName, organization, duration, url);
                    return newWebcast;
                }
                return null;
            });

            Optional<Webcast> result = dialog.showAndWait();
            result.ifPresent(webcast -> {
                // Add the new webcast to the list
                webcastsList.add(webcast);

                // Add the new webcast to the table
                webcastTable.getItems().add(webcast);

                // Refresh the table view to display the new webcast
                webcastTable.refresh();

                // Print all webcasts in the table
                webcastTable.getItems().forEach(System.out::println);

                // Print the new webcast
                System.out.println(webcast);
                // Scroll to the new row in the table
                webcastTable.scrollTo(webcast);
            });
        });

        updateButton.setOnAction(eventUpdate -> {
// Get the selected webcast from the table
            Webcast updateSelectedWebcast = webcastTable.getSelectionModel().getSelectedItem();
            if (updateSelectedWebcast == null) {
// No webcast selected, show an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No webcast selected.");
                alert.showAndWait();
                return;

            }
// Show a dialog to get the webcast information
            Dialog<Webcast> updateDialog = new Dialog<>();
            updateDialog.setTitle("Webcast aanpassen");
            updateDialog.setHeaderText("Voer de nieuwe gegevens van de webcast in:");

// Create the dialog content
            GridPane updateGrid = new GridPane();
            updateGrid.setHgap(10);
            updateGrid.setVgap(10);
            updateGrid.setPadding(new Insets(20, 150, 10, 10));

            TextField updatedTitleField = new TextField(updateSelectedWebcast.getTitle());
            updatedTitleField.setPromptText("Title");
            TextArea updatedDescriptionField = new TextArea(updateSelectedWebcast.getDescription());
            updatedDescriptionField.setPromptText("Description");
            TextField updatedSpeakerNameField = new TextField(updateSelectedWebcast.getSpeakerName());
            updatedSpeakerNameField.setPromptText("Speaker Name");
            TextField updatedOrganizationField = new TextField(updateSelectedWebcast.getOrganization());
            updatedOrganizationField.setPromptText("Organization");
            Spinner<Integer> updatedDurationField = new Spinner<>(1, 300, updateSelectedWebcast.getDuration());
            updatedDurationField.setEditable(true);
            updatedDurationField.setPrefWidth(60);
            updatedDurationField.setPromptText("Duration (min)");
            TextField updatedUrlField = new TextField(updateSelectedWebcast.getUrl());
            updatedUrlField.setPromptText("URL");

// Add the input fields to a GridPane
            GridPane inputGridPane = new GridPane();
            inputGridPane.setHgap(10);
            inputGridPane.setVgap(10);
            inputGridPane.add(new Label("Title:"), 0, 0);
            inputGridPane.add(updatedTitleField, 1, 0);
            inputGridPane.add(new Label("Description:"), 0, 1);
            inputGridPane.add(updatedDescriptionField, 1, 1);
            inputGridPane.add(new Label("Speaker Name:"), 0, 2);
            inputGridPane.add(updatedSpeakerNameField, 1, 2);
            inputGridPane.add(new Label("Organization:"), 0, 3);
            inputGridPane.add(updatedOrganizationField, 1, 3);
            inputGridPane.add(new Label("Duration (min):"), 0, 4);
            inputGridPane.add(updatedDurationField, 1, 4);
            inputGridPane.add(new Label("URL:"), 0, 5);
            inputGridPane.add(updatedUrlField, 1, 5);
            updateDialog.getDialogPane().setContent(inputGridPane);

            // Add the OK and Cancel buttons to the dialog
            ButtonType updateOkButtonType = new ButtonType("Update", ButtonData.OK_DONE);
            updateDialog.getDialogPane().getButtonTypes().addAll(updateOkButtonType, ButtonType.CANCEL);

            // Convert the dialog result to a webcast object when the OK button is clicked
            updateDialog.setResultConverter(dialogButton -> {
                if (dialogButton == updateOkButtonType) {
                    String title = updatedTitleField.getText();
                    String description = updatedDescriptionField.getText();
                    String speakerName = updatedSpeakerNameField.getText();
                    String organization = updatedOrganizationField.getText();
                    int duration = updatedDurationField.getValue();
                    String url = updatedUrlField.getText();
                    return new Webcast(title, description, speakerName, organization, duration, url);
                }
                return null;
            });

            Optional<Webcast> updateResult = updateDialog.showAndWait();

            if (updateResult.isPresent()) {
                Webcast updatedWebcast = updateResult.get();
                if (updatedWebcast != null) {
                    // Update the selected webcast in the table with the new information
                    updateSelectedWebcast.setTitle(updatedWebcast.getTitle());
                    updateSelectedWebcast.setDescription(updatedWebcast.getDescription());
                    updateSelectedWebcast.setSpeakerName(updatedWebcast.getSpeakerName());
                    updateSelectedWebcast.setOrganization(updatedWebcast.getOrganization());
                    updateSelectedWebcast.setDuration(updatedWebcast.getDuration());
                    updateSelectedWebcast.setUrl(updatedWebcast.getUrl());
                    // Refresh the table to reflect the changes
                    webcastTable.refresh();
                }
            }
        });

        deleteButton.setOnAction(ev -> {
            // Get the selected webcast from the table
            Webcast deleteSelectedWebcast = webcastTable.getSelectionModel().getSelectedItem();
            if (deleteSelectedWebcast == null) {
                // No webcast selected, show an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No webcast selected.");
                alert.showAndWait();

            }

            // Show a confirmation dialog before deleting the webcast
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm deletion");
            alert.setHeaderText("Are you sure you want to delete the selected webcast?");
            alert.setContentText(deleteSelectedWebcast.getTitle());

            Optional<ButtonType> buttonResult = alert.showAndWait();
            if (buttonResult.isPresent() && buttonResult.get() == ButtonType.OK) {
                // Remove the selected webcast from the list and the table
                webcastsList.remove(deleteSelectedWebcast);
                webcastTable.getItems().remove(deleteSelectedWebcast);
            }

        });
        return createTopThreeWebcastsPane;
    }
}
