package com.example.monitoramento;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
        info = @Info(
                title = "Projeto CFC",
                version = "1.0.0",
                description = "API de gerenciamento de aulas práticas e teóricas pelos CFC's do Brasil",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                contact = @Contact(
                        name = "Luis Felipe Rodrigues",
                        email = "luisfelipebr1995@gmail.com"
                )
        )

)
public class MonitoramentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoramentoApplication.class, args);
    }

}
