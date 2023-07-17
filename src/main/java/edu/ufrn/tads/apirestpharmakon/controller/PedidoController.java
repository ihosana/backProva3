package edu.ufrn.tads.apirestpharmakon.controller;

import edu.ufrn.tads.apirestpharmakon.domain.Pedido;
import edu.ufrn.tads.apirestpharmakon.service.PedidoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    PedidoService service;
    ModelMapper mapper;

    public PedidoController(PedidoService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido.DtoResponse create(@RequestBody @Valid Pedido.DtoRequest p){

        Pedido pedido = this.service.create(Pedido.DtoRequest.convertToEntity(p, mapper));

        Pedido.DtoResponse response = Pedido.DtoResponse.convertToDto(pedido, mapper);
        response.generateLinks(pedido.getId());
        return response;
    }

    @GetMapping
    public List<Pedido> list(){
        return this.service.list();
    }

    @PutMapping("{id}")
    public Pedido.DtoResponse update(@RequestBody Pedido.DtoRequest dtoRequest, @PathVariable Long id){
        Pedido p = Pedido.DtoRequest.convertToEntity(dtoRequest, mapper);
        Pedido.DtoResponse response = Pedido.DtoResponse.convertToDto(this.service.update(p, id), mapper);
        response.generateLinks(id);
        return response;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }

}
