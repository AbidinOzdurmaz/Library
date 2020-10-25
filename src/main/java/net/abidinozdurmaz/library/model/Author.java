package net.abidinozdurmaz.library.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
@NamedQuery(name = "Author.findAllSortName",query = "Select a from Author a order by a.name")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authorId;

    @Column(nullable = false)
    @NotBlank(message = "İsim boş olamaz")
    private String name;

    @Lob
    private String description;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(@NotBlank(message = "İsim boş olamaz") String name, String description) {
        this.name = name;
        this.description = description;
    }


}
