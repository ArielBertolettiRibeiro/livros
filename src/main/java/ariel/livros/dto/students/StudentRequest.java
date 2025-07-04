package ariel.livros.dto.students;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRequest{

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 200, message = "O campo nome deve ter no máximo 200 caracteres.")
    private String name;

    @NotBlank(message = "O registro do estudante é obrigatório.")
    @Pattern(
            regexp = "^[a-zA-Z0-9\\-]+$",
            message = "O registro deve conter apenas letras, números ou hífens."
    )
    @Size(max = 50, message = "O campo registro deve ter no máximo 50 caracteres.")
    private String registration;
}
