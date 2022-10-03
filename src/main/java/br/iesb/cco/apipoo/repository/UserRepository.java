package br.iesb.cco.apipoo.repository;

import br.iesb.cco.apipoo.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<List<UserEntity>> findByNameContaining(String name);

}