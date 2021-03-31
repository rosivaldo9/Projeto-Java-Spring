package rosivaldo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rosivaldo.domain.entity.Produto;

interface Produtos extends JpaRepository<Produto, Integer> {

}
