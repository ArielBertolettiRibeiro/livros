package ariel.livros.repository;

import ariel.livros.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);
}
