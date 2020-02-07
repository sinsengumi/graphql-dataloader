package net.sinsengumi.graphql.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import net.sinsengumi.graphql.Data;
import net.sinsengumi.graphql.type.Book;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BooksQueryResolver implements GraphQLQueryResolver {

    public List<Book> books() {
        log.info("Query books (DB access).");
        return Data.books;
    }
}
