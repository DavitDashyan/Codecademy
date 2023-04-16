package UnitTest;

import Tabs.Webcast;
import static org.junit.Assert.*;
import org.junit.Test;

//Deze tests maken gebruik van de JUnit 4-bibliotheek en testen verschillende methoden van de Webcast-klasse.
//Je kunt deze tests in uw favoriete IDE uitvoeren of vanaf de opdrachtregel met behulp van de opdracht mvn test als u Maven gebruikt.

public class WebcastTest {
    
    @Test
    public void testGetTitle() {
        Webcast webcast = new Webcast("Java Fundamentals", "Learn the basics of Java programming", "John Smith", "ABC Corp", 60, "https://example.com/java-fundamentals");
        assertEquals("Java Fundamentals", webcast.getTitle());
    }
    
    @Test
    public void testSetDescription() {
        Webcast webcast = new Webcast("Java Fundamentals", "Learn the basics of Java programming", "John Smith", "ABC Corp", 60, "https://example.com/java-fundamentals");
        webcast.setDescription("This webcast teaches the core concepts of Java programming");
        assertEquals("This webcast teaches the core concepts of Java programming", webcast.getDescription());
    }
    
    @Test
    public void testGetSpeakerName() {
        Webcast webcast = new Webcast("Java Fundamentals", "Learn the basics of Java programming", "John Smith", "ABC Corp", 60, "https://example.com/java-fundamentals");
        assertEquals("John Smith", webcast.getSpeakerName());
    }
    
    @Test
    public void testSetOrganization() {
        Webcast webcast = new Webcast("Java Fundamentals", "Learn the basics of Java programming", "John Smith", "ABC Corp", 60, "https://example.com/java-fundamentals");
        webcast.setOrganization("XYZ Inc");
        assertEquals("XYZ Inc", webcast.getOrganization());
    }
    
    @Test
    public void testGetDuration() {
        Webcast webcast = new Webcast("Java Fundamentals", "Learn the basics of Java programming", "John Smith", "ABC Corp", 60, "https://example.com/java-fundamentals");
        assertEquals(60, webcast.getDuration());
    }
    
    @Test
    public void testSetUrl() {
        Webcast webcast = new Webcast("Java Fundamentals", "Learn the basics of Java programming", "John Smith", "ABC Corp", 60, "https://example.com/java-fundamentals");
        webcast.setUrl("https://example.com/java-fundamentals-2");
        assertEquals("https://example.com/java-fundamentals-2", webcast.getUrl());
    }
    
}

