package ariel.livros.dto.students;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentUpdateRequest {

    @NotBlank(message = "O campo nome é obrigatório.")
    @Size(max = 200, message = "O campo nome deve ter no máximo 200 caracteres.")
    private String name;
}
