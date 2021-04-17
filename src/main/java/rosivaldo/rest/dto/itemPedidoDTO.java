package rosivaldo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class itemPedidoDTO {

private  Integer produto;
private Integer quantidade;
private String statusPedido;

}
