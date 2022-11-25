package br.iesb.cco.apipoo.controller;

import br.iesb.cco.apipoo.model.BookEntity;
import br.iesb.cco.apipoo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/getbooks")
    public ResponseEntity<List<BookEntity>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<BookEntity> bookEntities = new ArrayList<BookEntity>();

            if (title == null)
                bookRepository.findAll().forEach(bookEntities::add);
            else
                bookRepository.findByTitleContaining(title).forEach(bookEntities::add);

            if (bookEntities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bookEntities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/savebook")
    public ResponseEntity<BookEntity> createTutorial(@RequestBody BookEntity bookEntity) {
        try {
            BookEntity _bookEntity = bookRepository
                    .save(new BookEntity(bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPublisher(), bookEntity.getPages(), bookEntity.getGenre()));
            return new ResponseEntity<>(_bookEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delbook/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delbooks")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
