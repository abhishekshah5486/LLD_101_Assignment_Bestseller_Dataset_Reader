import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class BookQueries {
    public static int countBooksByAuthor(List<Book> books, String authorName) {
        if (authorName == null) {
            return 0;
        }
        String target = authorName.trim().toLowerCase(Locale.ROOT);
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor() != null && book.getAuthor().trim().toLowerCase(Locale.ROOT).equals(target)) {
                count += 1;
            }
        }
        return count;
    }

    public static Set<String> getAllAuthors(List<Book> books) {
        Set<String> authors = new LinkedHashSet<String>();
        for (Book book : books) {
            if (book.getAuthor() != null && !book.getAuthor().trim().isEmpty()) {
                authors.add(book.getAuthor().trim());
            }
        }
        return authors;
    }

    public static List<Book> getBooksByAuthor(List<Book> books, String authorName) {
        List<Book> result = new ArrayList<Book>();
        if (authorName == null) {
            return result;
        }
        String target = authorName.trim().toLowerCase(Locale.ROOT);
        for (Book book : books) {
            if (book.getAuthor() != null && book.getAuthor().trim().toLowerCase(Locale.ROOT).equals(target)) {
                result.add(book);
            }
        }
        return result;
    }

    public static List<Book> getBooksByExactRating(List<Book> books, double rating) {
        List<Book> result = new ArrayList<Book>();
        double epsilon = 1e-9;
        for (Book book : books) {
            if (Math.abs(book.getUserRating() - rating) < epsilon) {
                result.add(book);
            }
        }
        return result;
    }

    public static List<Map.Entry<String, Integer>> getBookPricesByAuthor(List<Book> books, String authorName) {
        List<Map.Entry<String, Integer>> result = new ArrayList<Map.Entry<String, Integer>>();
        if (authorName == null) {
            return result;
        }
        String target = authorName.trim().toLowerCase(Locale.ROOT);
        for (Book book : books) {
            if (book.getAuthor() != null && book.getAuthor().trim().toLowerCase(Locale.ROOT).equals(target)) {
                result.add(new AbstractMap.SimpleEntry<String, Integer>(book.getTitle(), book.getPrice()));
            }
        }
        return result;
    }
}