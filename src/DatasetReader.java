import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatasetReader {
    public static List<Book> read(String csvAbsolutePath) {
        List<Book> books = new ArrayList<Book>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(csvAbsolutePath));
            String header = bufferedReader.readLine();
            if (header == null) {
                return books;
            }
            String line;
            int lineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber = lineNumber + 1;
                if (line.trim().isEmpty()) {
                    continue;
                }
                List<String> columns = splitCsvRespectingQuotes(line);
                if (columns.size() < 7) {
                    continue;
                }
                if (!columns.isEmpty()) {
                    String first = columns.get(0);
                    if (first != null) {
                        columns.set(0, first.replace("\uFEFF", ""));
                    }
                }
                try {
                    String title = columns.get(0).trim();
                    String author = columns.get(1).trim();
                    double userRating = Double.parseDouble(columns.get(2).trim());
                    int reviews = Integer.parseInt(columns.get(3).trim());
                    int price = Integer.parseInt(columns.get(4).trim());
                    int year = Integer.parseInt(columns.get(5).trim());
                    String genre = columns.get(6).trim();
                    books.add(new Book(title, author, userRating, reviews, price, year, genre));
                } catch (NumberFormatException parseError) {
                    // Skip malformed numeric rows
                }
            }
        } catch (IOException ioException) {
            System.err.println("Failed to read CSV: " + ioException.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ignore) {
                }
            }
        }
        return books;
    }

    private static List<String> splitCsvRespectingQuotes(String line) {
        List<String> fields = new ArrayList<String>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i += 1;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        fields.add(current.toString());
        return fields;
    }
}