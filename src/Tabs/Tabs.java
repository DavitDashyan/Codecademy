
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
import Database.DbConnection;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.*;

public class Tabs extends Application {

    private DbConnection con = new DbConnection();
    private List<Student> studentsList = new ArrayList<>();

    private Scene mainScene;
    private Stage primaryStage;
    private BorderPane mainPane;
    private TabPane tabPane;
    static int userId = 500;
    static int addressId = 99;

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

        try {
            con.connectToDb();
            ResultSet res = con.getResult("SELECT * FROM student");

            while (res.next()) {
                int id = res.getInt("id");
                String email = res.getString("email");
                String name = res.getString("name");
                Date dateofbirth = res.getDate("birth_date");
                String gender = res.getString("gender");
                String addres = res.getString("address_id");
                //String postalcode = res.getString("postalcode");
                String city = res.getString("city");
                String country = res.getString("country");

                Calendar cal = Calendar.getInstance();
                cal.setTime(dateofbirth);

                int temp = cal.get(Calendar.MONTH);
                //TableRow row = new TableRow();
                LocalDate dateLoc = LocalDate.of(cal.get(Calendar.YEAR), 12, cal.get(Calendar.DAY_OF_MONTH));
                // LocalDate dateLoc = dateofbirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                Student row = new Student(email, name, dateLoc, gender, addres, city, country);
                studentsTable.getItems().add(row);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

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
                    addressId++;
                    String addres = Integer.toString(addressId);
                    userId++;

                    String strUserId = Integer.toString(userId);
                    try {
                        String SQL = "INSERT INTO student ( id, email, name, birth_date, gender,address_id ,city, country) VALUES ('"
                                + strUserId
                                + "','"
                                + email
                                + "','"
                                + name
                                + "','" + birthDate + "','" + gender + "','" + addres + "','" + city + "','" + country + "')";
                        con.execute(SQL);
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        return null;
                    }

                    return new Student(email, name, birthDate, gender, addres, city, country);
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
                    String addres = "";
                    String strUserId = Integer.toString(updateSelectedStudent.getId());
//                    try {
//                        String SQL = "UPDATE student SET email =" + email +
//                                ", name=" + name + 
//                                ", birth_date=" + birthDate +
//                                ", gender="+ gender +
//                                ", address_id = " + addres +
//                                ", city=" +city+
//                                ", country =" + country+
//                                " WHERE id =" + 
//                                 strUserId
//                                ;
//                        con.execute(SQL);
//                    } catch (SQLException ex) {
//                        System.out.println(ex);
//                        return null;
//                    }
                    
                    return new Student(email, name, birthDate, gender, addres, city, country);
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
                
//                    String strUserId = Integer.toString(deleteSelectedStudent.getId());
//                    try {
//                        String SQL = "Delete FROM student where id = " + strUserId;
//                        con.execute(SQL);
//                    } catch (SQLException ex) {
//                        System.out.println(ex);
//                        return null;
//                    }
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
