package rosivaldo.service;


import rosivaldo.domain.entity.pedido;
import rosivaldo.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    pedido salvar(PedidoDTO dto);

    Optional<pedido> obterPedidoCompleto(Integer  id);
}
