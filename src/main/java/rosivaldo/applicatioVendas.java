package rosivaldo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rosivaldo.domain.entity.Cliente;
import rosivaldo.domain.repository.Clientes;

@SpringBootApplication
public class applicatioVendas {

    @Bean
    public CommandLineRunner fff(Clientes clientes){
        return  args -> {
            Cliente e = new Cliente(null,"Rosivaldo");
            clientes.save(e);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(applicatioVendas.class, args);
    }
}
