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
@NamedQuery(name = "Publisher.findAllSortName",query = "Select p from Publisher p order by p.name")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long publisherId;

    @NotBlank(message = "İsim boş olamaz")
    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;


    public Publisher(@NotBlank(message = "İsim boş olamaz") String name, String description) {
        this.name = name;
        this.description = description;
    }
}
