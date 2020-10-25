package net.abidinozdurmaz.library.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookDetail {
    private long id;
    private String name;
    private String subName;
    private String seriesName;
    private String authorName;
    private String publisherName;
    private String isbnNo;
    private String description;


}
