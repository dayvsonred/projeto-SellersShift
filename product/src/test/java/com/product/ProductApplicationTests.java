package com.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductApplicationTests {

	@Test
	void contextLoads() {
	}

//    @Autowired
//    private Flyway flyway;

    @BeforeEach
    public void setUp() {
//        flyway.clean();
//        flyway.migrate();
    }

}
