package rosivaldo.rest.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rosivaldo.domain.entity.ItemPedido;
import rosivaldo.domain.entity.pedido;
import rosivaldo.rest.dto.PedidoDTO;
import rosivaldo.rest.dto.informacaoItemPedidoDto;
import rosivaldo.rest.dto.informacoesPedidoDto;
import rosivaldo.service.PedidoService;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        System.out.println("dto é igual   "+dto);
        pedido pedido = service.salvar(dto);

        return pedido.getId();
    }

    @GetMapping("{id}")
    public informacoesPedidoDto getById(@PathVariable Integer id) {
        return service.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(()->
                        new ResponseStatusException(NOT_FOUND, "pedido não encontrado"));
    }


    public informacoesPedidoDto converter(pedido pedido){
       return informacoesPedidoDto
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .items(converter(pedido.getItens()))
               .status(pedido.getStatus().name())
               .build();
    }

    private List<informacaoItemPedidoDto> converter(List<ItemPedido> itens){

        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream()
                .map( item ->  informacaoItemPedidoDto.builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .quantidade(item.getQuantidade())
                        .build()).collect(Collectors.toList());
    }




}
