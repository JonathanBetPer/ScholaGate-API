package me.scholagate.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación - API RESTful ScholaGate Project
 * @version 1.0
 * @since 27/04/2024
 * @see me.scholagate.api.controllers
 * @see me.scholagate.api.services
 * @see me.scholagate.api.models
 * @see me.scholagate.api.dtos
 * @see me.scholagate.api.securities
 * @see me.scholagate.api.utils
 * @see me.scholagate.api.repositories
 * @see me.scholagate.api.swagger
 *
 */
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}