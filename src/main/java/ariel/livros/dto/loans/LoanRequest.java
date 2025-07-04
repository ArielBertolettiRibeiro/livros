package ariel.livros.dto.loans;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanRequest {

    @NotNull(message = "O ID do estudante é obrigatório.")
    @Positive(message = "O ID do estudante deve ser positivo.")
    private Long student;

    @NotNull(message = "O ID do livro é obrigatório.")
    @Positive(message = "O ID do livro deve ser positivo.")
    private Long book;
}
