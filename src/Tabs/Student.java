package Tabs;

import java.time.LocalDate;

public class Student {

    private static int lastUsedId = 0;

    private int id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String gender;
    private String city;
    private String country;

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

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
