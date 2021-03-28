package rosivaldo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rosivaldo.domain.entity.Cliente;
import rosivaldo.domain.repository.Clientes;

import java.util.List;

@SpringBootApplication
public class applicatioVendas {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args ->{

            System.out.println("Salvando clientes");
            Cliente clinte = new Cliente();
            clinte.setNome("Rosivaldo");
            clientes.salvar(clinte);

            Cliente clinte1 = new Cliente();
            clinte1.setNome("Tania");
            clientes.salvar(clinte1);

            Cliente clinte2 = new Cliente();
            clinte2.setNome("Arthur");
            clientes.salvar(clinte2);

            System.out.println("Listando clientes");
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

           System.out.println("Atualizando  clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clientes.atualizar(c);
            });
            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);


            System.out.println("buscando clientes por nome");
            clientes.buscarPorNome("sivald").forEach(System.out::println);

            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

           System.out.println("Deletando clientes");
            clientes.obterTodos().forEach(c ->{
                clientes.deletar(c.getId());
            });
            todosClientes = clientes.obterTodos();
          if( todosClientes.isEmpty()) {
              System.out.println("Nenhum cliente encontrado");
          }else {

              todosClientes.forEach(System.out::println);
          }

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(applicatioVendas.class, args);
    }
}
