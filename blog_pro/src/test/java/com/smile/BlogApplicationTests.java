package com.smile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
        String s = LocalDate.now().toString();
        System.out.println(s);
    }

}
