package com.ukar;

import com.ukar.annotation.test.AnnotationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jyou on 2017/9/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class AnnotationAndAspectTest {
    @Autowired
    private AnnotationTest annotationTest;

    @Test
    public void test() {
        String test = annotationTest.test();
        System.out.println(test);
    }

}
