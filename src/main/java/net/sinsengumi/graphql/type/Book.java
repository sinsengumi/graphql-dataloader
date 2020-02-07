package net.sinsengumi.graphql.type;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private int id;
    private String name;
    private int pageCount;
    private int authorId;
}
