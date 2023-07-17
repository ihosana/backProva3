package edu.ufrn.tads.apirestpharmakon.service;

import edu.ufrn.tads.apirestpharmakon.domain.Pessoa;
import edu.ufrn.tads.apirestpharmakon.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService extends GenericService<Pessoa, PessoaRepository> {

    public PessoaService(PessoaRepository repository) {
        super(repository);
    }

}
