package net.abidinozdurmaz.library.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name="Book.findAllBookDetail",
                query = "select new net.abidinozdurmaz.library.model.BookDetail(b.bookId,b.name,b.subName,b.seriesName,a.name,p.name,b.isbnNo,b.description) " +
                        "from Book b left join b.author a left join b.publisher p"),
        @NamedQuery(name="Book.findAllBookDetailById",
                query = "select new net.abidinozdurmaz.library.model.BookDetail(b.bookId,b.name,b.subName,b.seriesName,a.name,p.name,b.isbnNo,b.description) " +
                        "from Book b left join b.author a left join b.publisher p where b.bookId=?1"),
        @NamedQuery(name = "Book.findByIsbnNo",
                query = "select new net.abidinozdurmaz.library.model.BookDetail(b.bookId,b.name,b.subName,b.seriesName,a.name,p.name,b.isbnNo,b.description) " +
                        "from Book b left join b.author a left join b.publisher p where b.isbnNo = ?1")
})

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    @Column(nullable = false)
    @NotBlank(message = "İsim boş olamaz")
    private String name;

    private String subName;

    private String seriesName;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Publisher publisher;

    @Column(unique = true,nullable = false,length = 13)
    @NotBlank(message = "Isbn numarası boş olamaz")
    private String isbnNo;

    @Lob
    private String description;

    public Book(@NotBlank(message = "İsim boş olamaz") String name, String subName, String seriesName,
                @NotBlank(message = "Isbn numarası boş olamaz") String isbnNo, String description) {
        this.name = name;
        this.subName = subName;
        this.seriesName = seriesName;
        this.isbnNo = isbnNo;
        this.description = description;
    }
}
