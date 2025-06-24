package ariel.livros.service;

import ariel.livros.domain.entity.Book;
import ariel.livros.dto.books.BookRequest;
import ariel.livros.dto.books.BookResponse;
import ariel.livros.dto.books.BookSummary;
import ariel.livros.dto.books.BookUpdateRequest;
import ariel.livros.mapper.BookMapper;
import ariel.livros.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    public BookResponse creat(BookRequest request) {
        repository.findByTitleIgnoreCaseAndAuthorIgnoreCase(request.title(), request.author())
                .ifPresent(book -> {
                    throw new RuntimeException("Livro já existe!");
                });

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public BookResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("id não encontrado!"));
    }

    public Page<BookSummary> findAll(Pageable pageable) {
       return repository.findAll(pageable)
                .map(mapper::toSummary);
    }

    public BookResponse update(Long id, BookUpdateRequest request) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        book.setAuthor(request.author());
        book.setTitle(request.title());
        book.setPublicationYear(request.publicationYear());

        return mapper.toResponse(repository.save(book));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new RuntimeException("Id não encontrado");
        }
        repository.deleteById(id);
    }
}
