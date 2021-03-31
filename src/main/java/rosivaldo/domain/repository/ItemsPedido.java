package rosivaldo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rosivaldo.domain.entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
