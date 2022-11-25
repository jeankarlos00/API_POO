package br.iesb.cco.apipoo.repository;

import br.iesb.cco.apipoo.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByTitleContaining(String title);
}
