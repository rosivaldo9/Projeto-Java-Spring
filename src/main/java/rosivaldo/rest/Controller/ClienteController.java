package rosivaldo.rest.Controller;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rosivaldo.domain.entity.Cliente;
import rosivaldo.domain.repository.Clientes;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id){

     return clientes.findById(id).orElseThrow(()->
             new ResponseStatusException(HttpStatus.NOT_FOUND,
                     "cliente não encontrado"));
  }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clientes.save(cliente);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void delete(@PathVariable Integer id){

       clientes.findById(id)
               .map(client -> {
                   clientes.delete(client);
                   return client;
               })
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                                 @RequestBody Cliente cliente){
        clientes.findById(id)
                .map( clinteExist -> {
                    cliente.setId(clinteExist.getId());
                    clientes.save(cliente);
                    return  ResponseEntity.noContent().build();
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "cliente não encontrado"));

    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example example = Example.of(filtro, matcher);
        return  clientes.findAll(example);

    }
}
