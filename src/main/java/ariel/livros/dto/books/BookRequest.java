package ariel.livros.dto.books;

public record BookRequest(
        String title,
        String author,
        Integer publicationYear,
        Integer availableQuantity
) {}
