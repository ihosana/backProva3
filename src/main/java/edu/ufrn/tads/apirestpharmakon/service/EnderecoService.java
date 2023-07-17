package edu.ufrn.tads.apirestpharmakon.service;

import edu.ufrn.tads.apirestpharmakon.domain.Endereco;
import edu.ufrn.tads.apirestpharmakon.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends GenericService<Endereco, EnderecoRepository>{

    public EnderecoService(EnderecoRepository repository){
        super(repository);
    }
}
