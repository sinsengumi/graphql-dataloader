package net.sinsengumi.graphql.resolver;

import org.springframework.stereotype.Component;

import net.sinsengumi.graphql.Data;
import net.sinsengumi.graphql.type.Book;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BookByIdQueryResolver implements GraphQLQueryResolver {

    public Book bookById(int id) {
        log.info("Query bookById (DB access). id = {}", id);
        return Data.books.stream().filter(b -> b.getId() == id).findFirst().orElseGet(null);
    }
}
