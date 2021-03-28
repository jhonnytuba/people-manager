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
    // TODO: descrever o README.md
    // TODO: criar o build-and-deploy-image.sh

    public static void main(String[] args) {
        SpringApplication.run(PeopleManagerApplication.class, args);
    }

}
