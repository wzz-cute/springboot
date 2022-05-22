package com.wzz.io;

//import org.junit.Test;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class SpringbootIoApplicationTests {

    @Test
    void contextLoads() throws IOException {
        boolean newFile = new File("a.html").createNewFile();
    }

}
