package com.example.home_decoration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class HomeDecoration1ApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        String str = "123";
        System.out.println(Integer.valueOf(str));
    }

}
