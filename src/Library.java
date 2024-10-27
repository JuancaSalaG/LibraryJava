import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Library {
    List<Book> books = new ArrayList<Book>();
    private Scanner scanner = new Scanner(System.in);

    public Library() {
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-958-5193-1"));
        books.add(new Book("La autentica felicidad", "Martin Seligman", "978-958-5193-2"));
        books.add(new Book("El principito", "Antoine de Saint-Exupéry", "978-958-5193-3"));
    }

    public void menu() {
        while (true) {
            System.out.flush();
            System.out.println("===========================");
            System.out.println("Menu Library Java");
            System.out.println("===========================\n");
            System.out.println("Please select an option: (number)");
            System.out.println("1. Add a book");
            System.out.println("2. Delete a book");
            System.out.println("3. List all books");
            System.out.println("4. Search a book by autor or title");
            System.out.println("5. Exit\n");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter the title of the book:");
                    String title = scanner.nextLine();
                    System.out.println("Enter the author of the book:");
                    String author = scanner.nextLine();
                    String isbn = lastISBN();
                    int lastNumber = Integer.parseInt(isbn.split("-")[3]);
                    lastNumber++;
                    Book book = new Book(title, author, "978-958-5193-" + lastNumber);
                    books.add(book);
                    System.out.println("Book added successfully");
                    break;

                case 2:
                    System.out.println("Enter the ISBN of the book to delete: (978-958-5193-x)");
                    String isbnToDelete = scanner.nextLine();
                    Map.Entry<Boolean, Book> searchResult = searchBook(isbnToDelete);
                    if (!searchResult.getKey()) {
                        System.out.println("Book not found");
                        break;
                    }
                    books.remove(searchResult.getValue());
                    System.out.println("Book deleted successfully");
                    break;

                case 3:
                    System.out.println("All our books:");
                    printBooks(books);
                    break;

                case 4:
                    System.out.println("Enter the author or title to search:");
                    String search = scanner.nextLine();
                    Entry<Boolean, List<Book>> searchBooks = searchBookByTitleOrAuthor(search);
                    if (searchBooks.getKey()) {
                        System.out.println("Books found:");
                        printBooks(searchBooks.getValue());
                    } else {
                        System.out.println("There is no book with that author or title");                        
                    }
                    break;

                case 5:
                    System.out.println("Bye! Thanks for using the Library Java");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public String lastISBN() {
        return books.get(books.size() - 1).getIsbn();
    }

    public Map.Entry<Boolean, Book> searchBook(String isbn) {
        for (Book b: books) {
            if (b.getIsbn().equals(isbn)) {
                return new AbstractMap.SimpleEntry<Boolean, Book>(true, b);
            }
        }
        return new AbstractMap.SimpleEntry<Boolean, Book>(false, null);
    }

    public Entry<Boolean, List<Book>> searchBookByTitleOrAuthor(String search) {
        List<Book> result = new ArrayList<Book>();
        for (Book b: books) {
            if (b.getAuthor().toString().contains(search) || b.getTitle().toString().contains(search)) {
                result.add(b);
            }
        }
        if (result.size() > 0) {
            return new AbstractMap.SimpleEntry<Boolean, List<Book>>(true, result);
        }
        return new AbstractMap.SimpleEntry<Boolean, List<Book>>(false, null);
    }

    public void printBooks(List<Book> books) {
        for (Book b: books) {
            System.out.println(b);
        }
    }
}
