package rosivaldo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rosivaldo.domain.entity.Cliente;
import rosivaldo.domain.entity.pedido;

import java.util.List;
import java.util.Optional;

public interface Pedido extends JpaRepository<pedido, Integer> {
    List<pedido> findByCliente(Cliente cliente);


    @Query("select p from pedido  p left join fetch p.itens where p.id = :id")
    Optional<pedido> findByIdFecthItens(@Param("id") Integer id);
}
