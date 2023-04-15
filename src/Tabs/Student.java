package Tabs;

import java.time.LocalDate;

public class Student {

    private static int lastUsedId = 0;

    private final int id;
    private final String name;
    private final String email;
    private final LocalDate birthDate;
    private final String gender;
    private final String city;
    private final String country;

    public Student(String email, String name, LocalDate birthDate, String gender, String city, String country) {
        lastUsedId++;
        this.id = lastUsedId;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
