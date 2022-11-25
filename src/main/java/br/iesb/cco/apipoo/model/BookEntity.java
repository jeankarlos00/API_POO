package br.iesb.cco.apipoo.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publisher ", nullable = false)
    private String publisher;

    @Column(name = "pages ", nullable = false)
    private String pages ;

    @Column(name = "genre ", nullable = false)
    private String genre;

    public BookEntity() {

    }

    public BookEntity(String title, String author, String publisher, String pages, String genre) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.genre = genre;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pages='" + pages + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
