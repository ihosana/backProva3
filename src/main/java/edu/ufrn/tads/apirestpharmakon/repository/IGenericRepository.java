package edu.ufrn.tads.apirestpharmakon.repository;

import edu.ufrn.tads.apirestpharmakon.domain.AbstractEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IGenericRepository<E extends AbstractEntity> extends ListCrudRepository<E, Long> {

}
