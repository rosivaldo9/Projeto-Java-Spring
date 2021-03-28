package rosivaldo.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemPedido {

    private Integer id;
    private pedido cliente;
    private Produto dataPedido;
    private Integer quantidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public pedido getCliente() {
        return cliente;
    }

    public void setCliente(pedido cliente) {
        this.cliente = cliente;
    }

    public Produto getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Produto dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
