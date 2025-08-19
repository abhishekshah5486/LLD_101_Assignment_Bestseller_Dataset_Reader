public class Book {
    private String title;
    private String author;
    private double userRating;
    private int reviews;
    private int price;
    private int year;
    private String genre;

    public Book(
        String title,
        String author,
        double userRating,
        int reviews,
        int price,
        int year,
        String genre
    ) {
        this.title = title;
        this.author = author;
        this.userRating = userRating;
        this.reviews = reviews;
        this.price = price;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void printDetails() {
        String details = String.format(
            "Title: %s | Author: %s | Rating: %.1f | Reviews: %d | Price: $%d | Year: %d | Genre: %s",
            title,
            author,
            userRating,
            reviews,
            price,
            year,
            genre
        );
        System.out.println(details);
    }

    @Override
    public String toString() {
        return String.format(
            "Book{title=\"%s\", author=\"%s\", userRating=%.1f, reviews=%d, price=%d, year=%d, genre=\"%s\"}",
            title,
            author,
            userRating,
            reviews,
            price,
            year,
            genre
        );
    }
}