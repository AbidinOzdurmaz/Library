package net.abidinozdurmaz.library.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDetail {
    private long id;
    private String name;
    private String subName;
    private String seriesName;
    private String authorName;
    private String publisherName;
    private String isbnNo;
    private String description;

    public BookDetail(long id, String name, String subName, String seriesName, String authorName, String publisherName, String isbnNo, String description) {
        this.id = id;
        this.name = name;
        this.subName = subName;
        this.seriesName = seriesName;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.isbnNo = isbnNo;
        this.description = description;
    }

}
