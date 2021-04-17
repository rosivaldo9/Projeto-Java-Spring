package rosivaldo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rosivaldo.rest.dto.itemPedidoDTO;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {

    private  Integer cliente;
    private BigDecimal total;
    private List<itemPedidoDTO> items;
    private String statusPedido;


}
