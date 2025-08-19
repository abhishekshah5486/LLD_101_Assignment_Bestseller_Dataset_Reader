import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Driver {
    private static final String DEFAULT_DATASET_ABSOLUTE_PATH = 
        "/Users/abhishekshah/23bcs10065_LLD_101_Assignment_19.08.2025/bestsellers with categories.csv";

    public static void main(String[] args) {
        String datasetPath = args != null && args.length > 0 ? args[0] : DEFAULT_DATASET_ABSOLUTE_PATH;
        List<Book> books = DatasetReader.read(datasetPath);
        if (books.isEmpty()) {
            System.err.println("No books loaded. Check the CSV path: " + datasetPath);
            return;
        }

        System.out.println("Loaded books: " + books.size());
        System.out.println("Dataset: " + datasetPath);
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) {
                System.out.println("Exiting.");
                break;
            }
            if (choice.equals("1")) {
                System.out.print("Enter author name: ");
                String author = scanner.nextLine();
                int count = BookQueries.countBooksByAuthor(books, author);
                System.out.println("Total number of books by '" + author + "': " + count);
            } else if (choice.equals("2")) {
                Set<String> authors = BookQueries.getAllAuthors(books);
                System.out.println("Authors (" + authors.size() + "):");
                for (String a : authors) {
                    System.out.println("- " + a);
                }
            } else if (choice.equals("3")) {
                System.out.print("Enter author name: ");
                String author = scanner.nextLine();
                List<Book> authoredBooks = BookQueries.getBooksByAuthor(books, author);
                if (authoredBooks.isEmpty()) {
                    System.out.println("No books found for author '" + author + "'.");
                } else {
                    System.out.println("Books by '" + author + "' (" + authoredBooks.size() + "):");
                    for (Book b : authoredBooks) {
                        System.out.println("- " + b.getTitle());
                    }
                }
            } else if (choice.equals("4")) {
                System.out.print("Enter exact user rating (e.g., 4.7): ");
                String ratingStr = scanner.nextLine();
                try {
                    double rating = Double.parseDouble(ratingStr.trim());
                    List<Book> ratedBooks = BookQueries.getBooksByExactRating(books, rating);
                    System.out.println("Books with rating " + rating + " (" + ratedBooks.size() + "):");
                    for (Book b : ratedBooks) {
                        System.out.println("- " + b.getTitle() + " (" + b.getAuthor() + ")");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rating.");
                }
            } else if (choice.equals("5")) {
                System.out.print("Enter author name: ");
                String author = scanner.nextLine();
                List<Map.Entry<String, Integer>> result = BookQueries.getBookPricesByAuthor(books, author);
                if (result.isEmpty()) {
                    System.out.println("No books found for author '" + author + "'.");
                } else {
                    System.out.println("Titles and prices for '" + author + "':");
                    for (Map.Entry<String, Integer> entry : result) {
                        System.out.println("- " + entry.getKey() + ": $" + entry.getValue());
                    }
                }
            } else if (choice.equals("6")) {
                int preview = Math.min(5, books.size());
                for (int i = 0; i < preview; i++) {
                    books.get(i).printDetails();
                }
            } else {
                System.out.println("Unknown option.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1) Total number of books by an author");
        System.out.println("2) All the authors in the dataset");
        System.out.println("3) Names of all the books by an author");
        System.out.println("4) Classify with a user rating (exact match)");
        System.out.println("5) Price of all the books by an author");
        System.out.println("6) Preview first 5 parsed books");
        System.out.println("0) Exit");
        System.out.print("> ");
    }
}