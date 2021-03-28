package br.com.jhonatanserafim.peoplemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableJpaAuditing
@EnableSpringDataWebSupport
public class PeopleManagerApplication {

    // TODO: criar testes

    public static void main(String[] args) {
        SpringApplication.run(PeopleManagerApplication.class, args);
    }

}
