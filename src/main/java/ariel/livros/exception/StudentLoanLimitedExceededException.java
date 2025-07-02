package ariel.livros.exception;

public class StudentLoanLimitedExceededException extends RuntimeException {
    public StudentLoanLimitedExceededException(String message) {
        super(message);
    }
}
