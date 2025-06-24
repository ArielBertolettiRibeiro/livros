package ariel.livros.mapper;

import ariel.livros.domain.entity.Book;
import ariel.livros.dto.books.BookRequest;
import ariel.livros.dto.books.BookResponse;
import ariel.livros.dto.books.BookSummary;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final ModelMapper mapper;

    public Book toEntity(BookRequest request) {
        return mapper.map(request, Book.class);
    }

    public BookResponse toResponse(Book book) {
        return mapper.map(book, BookResponse.class);
    }

    public BookSummary toSummary(Book book){
        return mapper.map(book, BookSummary.class);
    }
}
