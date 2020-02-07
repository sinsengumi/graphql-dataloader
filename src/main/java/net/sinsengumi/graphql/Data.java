package net.sinsengumi.graphql;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import net.sinsengumi.graphql.type.Author;
import net.sinsengumi.graphql.type.Book;

public class Data {

    public static List<Book> books = Arrays.asList(
            new Book(1, "エリック・エヴァンスのドメイン駆動設計", 223, 10),
            new Book(2, "後悔しないためのVueコンポーネント設計", 635, 20),
            new Book(3, "正しいものを正しくつくる", 371, 30));

    public static List<Author> authors = Arrays.asList(
            new Author(10, "エリック", "エヴァンス"),
            new Author(20, "直博", "中島"),
            new Author(30, "聡啓", "市谷"));

    public static Author authorById(int id) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        return authors.stream()
                .filter(a -> a.getId() == id)
                .findFirst().orElse(null);
    }

    public static List<Author> authorsByIds(List<Integer> ids) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        return authors.stream()
                .filter(a -> ids.contains(a.getId()))
                .collect(Collectors.toList());
    }
}
