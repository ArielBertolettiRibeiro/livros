package ariel.livros.service.validator.student;

import ariel.livros.domain.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentValidatorExecute {

    private final List<StudentValidation> validations;

    public void validateAll(Student student) {
        for (StudentValidation v: validations) {
            v.validate(student);
        }
    }
}
