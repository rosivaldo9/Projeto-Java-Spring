package rosivaldo.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cliente {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private  String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente" , fetch = FetchType.LAZY )
    private Set<pedido> pedidos;


    public void setPedidos(Set<pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<pedido> getPedidos() {
        return pedidos;
    }


    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
