package persistence;


import model.WebPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//tests making and getting json and to and from file of SavePage and LoadPage by comparing if they are the same
public class SaveAndLoadTests {
    private SavePage saving;
    private WebPage testPage;
    //add loadpage when created

    @BeforeEach
    void runBefore() {
        saving = new SavePage();
        testPage = new WebPage();
        //initialize loadpage when created
    }

    //TODO
    @Test
    void testMakeAndGetJson() {
        //Will complete after get mechanism to retrieve json
    }

    //TODO
    @Test
    void testToAndFromFile() {
        //Will complete after get mechanism to retrieve file
    }
}
