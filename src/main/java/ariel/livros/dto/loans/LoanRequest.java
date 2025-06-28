package ariel.livros.dto.loans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanRequest {
    private Long student;
    private Long book;
}
