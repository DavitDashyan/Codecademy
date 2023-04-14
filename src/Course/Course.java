///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Course;
//
//import java.util.List;
//
///**
// *
// * @author dashy
// */
//public class Course {
//    private String name;
//    private List<Module> modules;
//    private List<Account> accounts;
//
//    public Course(String name, List<Module> modules, List<Account> accounts) {
//        this.name = name;
//        this.modules = modules;
//        this.accounts = accounts;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public List<Module> getModules() {
//        return modules;
//    }
//
//    public List<Account> getAccounts() {
//        return accounts;
//    }
//
//    public double getCompletionPercentage() {
//        int numAccounts = accounts.size();
//        int numCompleted = 0;
//
//        for (Account account : accounts) {
//            if (account.hasCompletedCourse(this)) {
//                numCompleted++;
//            }
//        }
//
//        return (double) numCompleted / numAccounts * 100;
//    }
//
//    public double getModuleCompletionPercentage(Module module) {
//        double totalLength = module.getLength();
//        double totalProgress = 0;
//
//        for (Account account : accounts) {
//            if (account.hasEnrolledInCourse(this)) {
//                totalProgress += account.getModuleProgress(module);
//            }
//        }
//
//        return totalProgress / (accounts.size() * totalLength) * 100;
//    }
//}
//
//public class Module {
//    private String name;
//    private double length;
//
//    public Module(String name, double length) {
//        this.name = name;
//        this.length = length;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getLength() {
//        return length;
//    }
//}
//
//public class Account {
//    private String username;
//    private List<Course> enrolledCourses;
//    private List<Course> completedCourses;
//
//    public Account(String username) {
//        this.username = username;
//        enrolledCourses = new ArrayList<>();
//        completedCourses = new ArrayList<>();
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public List<Course> getEnrolledCourses() {
//        return enrolledCourses;
//    }
//
//    public List<Course> getCompletedCourses() {
//        return completedCourses;
//    }
//
//    public boolean hasEnrolledInCourse(Course course) {
//        return enrolledCourses.contains(course);
//    }
//
//    public boolean hasCompletedCourse(Course course) {
//        return completedCourses.contains(course);
//    }
//
//    public double getModuleProgress(Module module) {
//        for (Course course : enrolledCourses) {
//            if (course.getModules().contains(module)) {
//                return course.getModuleCompletionPercentage(module);
//            }
//        }
//
//        return 0;
//    }
//}
//
//public class MainApp extends Application {
//    private List<Course> courses;
//    private List<Account> accounts;
//
//    public void start(Stage primaryStage) {
//        // Initialize courses and accounts
//
//        // Task 1: Completion percentage for each course
//        for (Course course : courses) {
//            ProgressBar progressBar = new ProgressBar();
//            double completionPercentage = course.getCompletionPercentage();
//            progressBar.setProgress(completionPercentage / 100);
//            VBox vbox = new VBox(new Label(course.getName()), progressBar);
//            // Add vbox to scene
//        }
//
//        // Task 2: Module completion percentage for selected course
//        ComboBox<Course> courseComboBox = new ComboBox<>(FXCollections.observableArrayList(courses));
//        ComboBox<Module> moduleComboBox = new ComboBox<>();
//        courseComboBox.setOnAction(event -> {
//            Course selectedCourse = courseComboBox.getValue();
//            moduleComboBox.setItems(FXC
