package net.sinsengumi.graphql.resolver;

import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import net.sinsengumi.graphql.type.Author;
import net.sinsengumi.graphql.type.Book;

import com.coxautodev.graphql.tools.GraphQLResolver;

import graphql.kickstart.execution.context.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthorResolver implements GraphQLResolver<Book> {

//    public Author author(Book book) {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        Author author = Data.authorById(book.getAuthorId());
//        stopwatch.stop();
//
//        log.info("Field author (DB access). id = {}, elapsed = {} (ms)", book.getAuthorId(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
//        return author;
//    }

    public CompletableFuture<Author> author(Book book, DataFetchingEnvironment env) {
        GraphQLContext context = (GraphQLContext) env.getContext();
        DataLoader<Integer, Author> dataLoader =
                context.getDataLoaderRegistry().get().getDataLoader("authorsDataLoader");
        log.info("Field author (Non DB access). id = {}", book.getAuthorId());
        return dataLoader.load(book.getAuthorId());
    }
}
