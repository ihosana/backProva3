package edu.ufrn.tads.apirestpharmakon.controller;


import edu.ufrn.tads.apirestpharmakon.domain.Produto;
import edu.ufrn.tads.apirestpharmakon.service.ProdutoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    ProdutoService service;
    ModelMapper mapper;

    public ProdutoController(ProdutoService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto.DtoResponse create(@RequestBody @Valid Produto.DtoRequest p){

        Produto produto = this.service.create(Produto.DtoRequest.convertToEntity(p, mapper));

        Produto.DtoResponse response = Produto.DtoResponse.ConvertToDto(produto, mapper);
        response.generateLinks(produto.getId());
        return response;
    }

    @GetMapping
    public List<Produto> list(){
        return this.service.list();
    }

    @PutMapping("{id}")
    public Produto.DtoResponse update(@RequestBody Produto.DtoRequest dtoRequest, @PathVariable Long id){
        Produto p = Produto.DtoRequest.convertToEntity(dtoRequest, mapper);
        Produto.DtoResponse response = Produto.DtoResponse.ConvertToDto(this.service.update(p, id), mapper);
        response.generateLinks(id);
        return response;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }
}
