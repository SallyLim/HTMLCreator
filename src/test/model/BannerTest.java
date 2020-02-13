package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BannerTest {
    Banner banner;


    @BeforeEach
    void runBefore() {
        banner = new Banner();
    }

    @Test
    void testGetBannerColorDefault(){
        assertEquals("lavender", banner.getBannerColor());
    }

    @Test
    void testGetBannerColorAfterChange(){
        banner.changeBannerColor("red");

        assertEquals("red", banner.getBannerColor());
    }

}
