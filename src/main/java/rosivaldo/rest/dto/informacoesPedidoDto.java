package rosivaldo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class informacoesPedidoDto {

    private Integer codigo;
    private String cpf;
    private BigDecimal total;
    private String dataPedido;
    private String status;
    private String nomeCliente;
    private List<informacaoItemPedidoDto> items;
}
