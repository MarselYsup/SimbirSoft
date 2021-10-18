package ru.simbir.soft;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.simbir.soft.controller.ControllerURL;
import ru.simbir.soft.dao.WordsRepositoryImpl;
import ru.simbir.soft.services.HTMLParser;
import ru.simbir.soft.services.StringParser;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Properties property = new Properties();
        try {
            property.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(property.getProperty("db.driver"));
        dataSource.setUsername(property.getProperty("db.user"));
        dataSource.setPassword(property.getProperty("db.password"));
        dataSource.setUrl(property.getProperty("db.url"));
        ControllerURL controllerURL = new ControllerURL(new HTMLParser(),new StringParser(),
                new WordsRepositoryImpl(dataSource));
        System.out.print("Введите URL: ");
        String path = sc.next();
        controllerURL.countWords(path);



    }
}
