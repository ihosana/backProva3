package edu.ufrn.tads.apirestpharmakon.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.ufrn.tads.apirestpharmakon.domain.Pessoa;
import edu.ufrn.tads.apirestpharmakon.service.PessoaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins ="http://localhost:3000")
@RestController

@RequestMapping("/pessoas")
public class PessoaController {
    PessoaService service;
    ModelMapper mapper;

    public PessoaController(PessoaService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa.DtoResponse create(@RequestBody @Valid Pessoa.DtoRequest p){

        Pessoa pessoa = this.service.create(Pessoa.DtoRequest.convertToEntity(p, mapper));

        Pessoa.DtoResponse response = Pessoa.DtoResponse.convertToDto(pessoa, mapper);
        response.generateLinks(pessoa.getId());
        return response;
    }

    @GetMapping
    public List<Pessoa> list(){
        return this.service.list();

//        return this.service.list().stream().map(
//                elementoAtual -> {
//                    System.out.println(elementoAtual.getNome());
//                    Pessoa.DtoResponse response = Pessoa.DtoResponse.convertToDto(elementoAtual, mapper);
//                    response.generateLinks(elementoAtual.getId());
//                    return response;
//                }).toList();
    }

    @PutMapping("{id}")
    public Pessoa.DtoResponse update(@RequestBody Pessoa.DtoRequest dtoRequest, @PathVariable Long id){
        Pessoa p = Pessoa.DtoRequest.convertToEntity(dtoRequest, mapper);
        Pessoa.DtoResponse response = Pessoa.DtoResponse.convertToDto(this.service.update(p, id), mapper);
        response.generateLinks(id);
        return response;
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }
}
