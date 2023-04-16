package UnitTest;


import Tabs.*;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

//De bovenstaande tests zijn bedoeld om de basisfunctionaliteit van de Student klasse te testen. 

public class StudentTest {

    /**
     * 
     */
    @Test
    public void testConstructor() {
        String email = "test@example.com";
        String name = "Test Student";
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        String gender = "Male";
        String city = "Test City";
        String country = "Test Country";

        Student student = new Student(email, name, birthDate, gender, city, country);

        assertEquals(email, student.getEmail());
        assertEquals(name, student.getName());
        assertEquals(birthDate, student.getBirthDate());
        assertEquals(gender, student.getGender());
        assertEquals(city, student.getCity());
        assertEquals(country, student.getCountry());
    }

    @Test
    public void testSettersAndGetters() {
        Student student = new Student("test@example.com", "Test Student", LocalDate.of(2000, 1, 1),
                "Male", "Test City", "Test Country");

        student.setId(1);
        assertEquals(1, student.getId());

        student.setName("New Name");
        assertEquals("New Name", student.getName());

        student.setEmail("new@example.com");
        assertEquals("new@example.com", student.getEmail());

        student.setBirthDate(LocalDate.of(2001, 2, 2));
        assertEquals(LocalDate.of(2001, 2, 2), student.getBirthDate());

        student.setGender("Female");
        assertEquals("Female", student.getGender());

        student.setCity("New City");
        assertEquals("New City", student.getCity());

        student.setCountry("New Country");
        assertEquals("New Country", student.getCountry());
    }
}
