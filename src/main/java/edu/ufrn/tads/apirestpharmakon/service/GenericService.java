package edu.ufrn.tads.apirestpharmakon.service;

import edu.ufrn.tads.apirestpharmakon.domain.AbstractEntity;
import edu.ufrn.tads.apirestpharmakon.repository.IGenericRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<E extends AbstractEntity, R extends IGenericRepository> implements IGenericService<E>{

    R repository;

    public GenericService(R repository){
        this.repository = repository;
    }

    @Override
    public E create(E e) {
        return (E) this.repository.save(e);
    }

    @Override
    public E update(E e, Long id) {
        Optional<E> pessoaBanco = repository.findById(id);
        if (pessoaBanco.isPresent()){
            return (E) this.repository.save(e);
        }else{
            throw  new EntityNotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<E> list() {
        return (List<E>) this.repository.findAll();
    }

}
