package net.sinsengumi.graphql.resolver;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import net.sinsengumi.graphql.Data;
import net.sinsengumi.graphql.type.Author;

import com.google.common.base.Stopwatch;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.servlet.context.DefaultGraphQLServletContext;
import graphql.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.servlet.context.GraphQLServletContextBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext(buildDataLoaderRegistry(), null);
    }

    @Override
    public GraphQLContext build(HttpServletRequest request, HttpServletResponse response) {
        return DefaultGraphQLServletContext.createServletContext(buildDataLoaderRegistry(), null).with(request).with(response).build();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext(buildDataLoaderRegistry(), null).with(session).with(handshakeRequest).build();
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
        dataLoaderRegistry.register("authorsDataLoader",
                new DataLoader<Integer, Author>(authorIds -> CompletableFuture.supplyAsync(() -> {
                    Stopwatch stopwatch = Stopwatch.createStarted();
                    List<Author> authors = Data.authorsByIds(authorIds);
                    stopwatch.stop();
                    log.info("DataLoader authors (DB access). ids = {}, elapsed = {} (ms)", authorIds, stopwatch.elapsed(TimeUnit.MILLISECONDS));
                    return authors;
                })));
        return dataLoaderRegistry;
    }
}
