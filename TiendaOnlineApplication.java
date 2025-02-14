package org.example.tiendaonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TiendaOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaOnlineApplication.class, args);
    }

}
