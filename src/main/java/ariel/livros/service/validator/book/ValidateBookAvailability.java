package ariel.livros.service.validator.book;

import ariel.livros.domain.entity.Book;
import ariel.livros.exception.BookUnavailableException;

public class ValidateBookAvailability implements BookValidation {
    @Override
    public void validate(Book book) {
        if (book.getAvailableQuantity() <= 0) {
            throw new BookUnavailableException("Livro indisponível para empréstimo!");
        }
    }
}
