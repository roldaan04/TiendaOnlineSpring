package org.example.tiendapractica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TiendaPracticaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaPracticaApplication.class, args);
    }

}
