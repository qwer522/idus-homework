package com.idus.homework;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ConfigurationPropertiesScan("com.idus.homework")
public class ApplicationTest {
    void contextLoad() {}
}