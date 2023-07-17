package edu.ufrn.tads.apirestpharmakon.service;

import edu.ufrn.tads.apirestpharmakon.domain.Pedido;
import edu.ufrn.tads.apirestpharmakon.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService extends GenericService<Pedido, PedidoRepository> {

    public PedidoService(PedidoRepository repository) {
        super(repository);
    }
}
