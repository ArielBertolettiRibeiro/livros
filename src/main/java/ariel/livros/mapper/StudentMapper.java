package ariel.livros.mapper;

import ariel.livros.domain.entity.Student;
import ariel.livros.dto.students.StudentRequest;
import ariel.livros.dto.students.StudentResponse;
import ariel.livros.dto.students.StudentSummary;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final ModelMapper mapper;

    public StudentResponse toResponse(Student student) {
        return mapper.map(student, StudentResponse.class);
    }

    public Student toEntity(StudentRequest request) {
        return mapper.map(request, Student.class);
    }

    public StudentSummary toSummary(Student student) {
        return mapper.map(student, StudentSummary.class);
    }
}
