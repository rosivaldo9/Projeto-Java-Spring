package rosivaldo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rosivaldo.domain.entity.Cliente;
import rosivaldo.domain.entity.ItemPedido;
import rosivaldo.domain.entity.Produto;
import rosivaldo.domain.entity.enumm.StatusPedido;
import rosivaldo.domain.entity.pedido;
import rosivaldo.domain.repository.Clientes;
import rosivaldo.domain.repository.ItemsPedido;
import rosivaldo.domain.repository.Pedido;
import rosivaldo.domain.repository.Produtos;
import rosivaldo.rest.dto.PedidoDTO;
import rosivaldo.rest.dto.itemPedidoDTO;
import rosivaldo.exception.regraNegocioExceptiom;
import rosivaldo.service.PedidoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceimpl implements PedidoService {

    private final Pedido repository;
    private final Clientes clienteRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemPedidoRepository;



    @Override
    @Transactional
    public pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() ->
                new regraNegocioExceptiom("codigo do cliente invalido")
        );

        pedido pedido = new pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedido = converteItems(pedido, dto.getItems());
        repository.save(pedido);
        itemPedidoRepository.saveAll(itemPedido);

        pedido.setItens(itemPedido);
        return pedido;
    }

    @Override
    public Optional<pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFecthItens(id);
    }

    private List<ItemPedido> converteItems(pedido pedido, List<itemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new regraNegocioExceptiom("não é possivel realizar um pedido sem items");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() ->
                                    new regraNegocioExceptiom("codigo de produto não encontrado: " + idProduto)
                            );

                    ItemPedido itempedido = new ItemPedido();
                    itempedido.setQuantidade(dto.getQuantidade());
                    itempedido.setPedido(pedido);
                    itempedido.setProduto(produto);
                    return itempedido;

                }).collect(Collectors.toList());
    }

}
