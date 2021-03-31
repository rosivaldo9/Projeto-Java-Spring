package rosivaldo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rosivaldo.domain.entity.Cliente;
import rosivaldo.domain.repository.Clientes;

import java.util.Optional;

@Controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/cliente/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id){

     Optional<Cliente> cliente= clientes.findById(id);

     if(cliente.isPresent()){
         return ResponseEntity.ok(cliente.get());
     }
         return ResponseEntity.notFound().build();

    }
}
