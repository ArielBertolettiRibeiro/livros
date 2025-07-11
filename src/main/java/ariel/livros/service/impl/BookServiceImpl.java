package ariel.livros.service.impl;

import ariel.livros.domain.entity.Book;
import ariel.livros.dto.books.BookRequest;
import ariel.livros.dto.books.BookResponse;
import ariel.livros.dto.books.BookSummary;
import ariel.livros.dto.books.BookUpdateRequest;
import ariel.livros.exception.BookNotFoundException;
import ariel.livros.exception.BookUnavailableException;
import ariel.livros.exception.DuplicationBookException;
import ariel.livros.mapper.BookMapper;
import ariel.livros.repository.BookRepository;
import ariel.livros.service.interfaces.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @Transactional
    @Override
    public BookResponse create(BookRequest request) {
        repository.findByTitleIgnoreCaseAndAuthorIgnoreCase(request.getTitle(), request.getAuthor())
                .ifPresent(book -> {
                    throw new DuplicationBookException("Livro já existe!");
                });

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public BookResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new BookNotFoundException("id não encontrado!"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BookSummary> findAll(Pageable pageable) {
        Page<Book> books = repository.findAll(pageable);
        return books.map(mapper::toSummary);

    }

    @Transactional
    @Override
    public BookResponse update(Long id, BookUpdateRequest request) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Id não encontrado!"));

        book.setAuthor(request.getAuthor());
        book.setTitle(request.getTitle());
        book.setPublicationYear(request.getPublicationYear());

        return mapper.toResponse(repository.save(book));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new BookNotFoundException("Id não encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public void reserve(Book book) {
        if (book.getAvailableQuantity() <= 0) {
            throw new BookUnavailableException("Livro indisponível!");
        }

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
    }

    @Override
    public void returnBook(Book book) {
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
    }
}
