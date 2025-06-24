package ariel.livros.dto.books;

public record BookResponse(
        Long id,
        String title,
        String author,
        Integer publicationYear,
        Integer availableQuantity
) {}
