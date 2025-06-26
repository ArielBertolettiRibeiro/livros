package ariel.livros.service.impl;

import ariel.livros.domain.entity.Student;
import ariel.livros.dto.students.StudentRequest;
import ariel.livros.dto.students.StudentResponse;
import ariel.livros.dto.students.StudentUpdateRequest;
import ariel.livros.mapper.StudentMapper;
import ariel.livros.repository.StudentRepository;
import ariel.livros.service.interfaces.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Transactional
    @Override
    public StudentResponse create(StudentRequest request) {
        repository.findByRegistration(request.registration())
                .ifPresent(students -> {
                    throw new RuntimeException("Aluno já existe!");
                });

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public StudentResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<StudentResponse> findAll(Pageable pageable) {
        Page<Student> students = repository.findAll(pageable);
        return students.map(mapper::toResponse);
    }

    @Transactional
    @Override
    public StudentResponse update(Long id, StudentUpdateRequest request) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student não encontrado"));

        student.setName(request.name());

        return mapper.toResponse(repository.save(student));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new RuntimeException("Id não encontrado!");
        }
        repository.deleteById(id);
    }

}
