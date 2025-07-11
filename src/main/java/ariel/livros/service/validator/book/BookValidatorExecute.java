package ariel.livros.service.validator.book;

import ariel.livros.domain.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookValidatorExecute {

    private final List<BookValidation> validations;

    public void validateAll(Book book) {
        for (BookValidation v: validations) {
            v.validate(book);
        }
    }
}
