package edu.ufrn.tads.apirestpharmakon.service;

import edu.ufrn.tads.apirestpharmakon.domain.Produto;
import edu.ufrn.tads.apirestpharmakon.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends GenericService<Produto, ProdutoRepository> {
    public ProdutoService(ProdutoRepository repository) {
        super(repository);
    }
}
