package ariel.livros.controllers;

import ariel.livros.dto.books.BookRequest;
import ariel.livros.dto.books.BookResponse;
import ariel.livros.dto.books.BookSummary;
import ariel.livros.dto.books.BookUpdateRequest;
import ariel.livros.service.impl.BookServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("book")
public class BookController {

    private final BookServiceImpl service;

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest request, UriComponentsBuilder uriBuilder) {
        BookResponse book = service.create(request);

        var uri = uriBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(uri).body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<BookSummary>> findAll(@PageableDefault(size = 10, sort = "author")Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public
    ResponseEntity<BookResponse> update(@PathVariable Long id, @RequestBody @Valid BookUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
