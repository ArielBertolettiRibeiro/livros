package ariel.livros.exception;

public class DuplicationBookException extends RuntimeException {
    public DuplicationBookException(String message) {
        super(message);
    }
}
