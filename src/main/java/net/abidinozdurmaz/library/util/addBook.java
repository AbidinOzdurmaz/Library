package net.abidinozdurmaz.library.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class addBook {

    private long bookId;

    private String name;

    private String subName;

    private String seriesName;

    private long author;

    private long publisher;

    private String isbnNo;

    private String description;
}
