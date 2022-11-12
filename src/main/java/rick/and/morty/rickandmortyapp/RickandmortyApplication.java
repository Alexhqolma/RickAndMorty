package rick.and.morty.rickandmortyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RickandmortyApplication {
    public static void main(String[] args) {
        SpringApplication.run(RickandmortyApplication.class, args);
    }

}
