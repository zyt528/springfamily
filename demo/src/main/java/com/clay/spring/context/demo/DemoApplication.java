package com.clay.spring.context.demo;

import com.clay.spring.context.demo.context.TestBean;
import com.clay.spring.context.demo.foo.FooConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@Slf4j
public class DemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(FooConfig.class);
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"}, annotationConfigApplicationContext);

        TestBean testBean = annotationConfigApplicationContext.getBean("testBeanX", TestBean.class);
        testBean.hello();

        log.info("================================");

        testBean = classPathXmlApplicationContext.getBean("testBeanX", TestBean.class);
        testBean.hello();

        testBean = classPathXmlApplicationContext.getBean("testBeanY", TestBean.class);
        testBean.hello();
    }
}
