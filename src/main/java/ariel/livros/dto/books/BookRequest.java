package ariel.livros.dto.books;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequest {

    @NotBlank(message = "O título não pode estar em branco.")
    @Size(max = 200, message = "O título deve ter no máximo 200 caracteres.")
    private String title;

    @NotBlank(message = "Campo autor não pode estar em branco.")
    @Size(max = 150, message = "O autor dece ter no máximo 150 caracteres.")
    private String author;

    @NotNull(message = "O ano de publicação é obrigatório.")
    @Positive(message = "O ano de publicação deve ser um número positivo.")
    private Integer publicationYear;


    @NotNull(message = "A quantidade de cadastro é obrigatório.")
    @Positive(message = "A quantidade deve ser um número positivo.")
    private Integer availableQuantity;
}
