package rosivaldo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rosivaldo.domain.entity.pedido;

public interface Pedido extends JpaRepository<pedido, Integer> {
}
