package rosivaldo.domain.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import rosivaldo.domain.entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value="select c from Cliente c where c.nome like '%:nome%'", nativeQuery=true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);


    @Query( "delete from Cliente c where c.nome= :nome")
    @Modifying
    void  deleteByNome(String nome);

    Boolean existsByNome(String  nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where  c.id = :id")
    Cliente findClienteFetchPeddidos(@Param("id") Integer id);









}
