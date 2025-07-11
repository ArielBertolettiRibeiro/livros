package ariel.livros.service.interfaces;

import ariel.livros.domain.entity.Book;
import ariel.livros.dto.books.BookRequest;
import ariel.livros.dto.books.BookResponse;
import ariel.livros.dto.books.BookSummary;
import ariel.livros.dto.books.BookUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {

    BookResponse create(BookRequest request);

    BookResponse findById(Long id);

    Page<BookSummary> findAll(Pageable pageable);

    BookResponse update(Long id, BookUpdateRequest request);

    void delete(Long id);

    void reserve(Book book);

    void returnBook(Book book);
}
