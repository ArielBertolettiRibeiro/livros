package ariel.livros.dto.books;

public record BookUpdateRequest(
        String title,
        String author,
        Integer publicationYear
) {}
