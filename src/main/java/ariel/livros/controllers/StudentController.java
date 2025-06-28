package ariel.livros.controllers;

import ariel.livros.dto.students.StudentRequest;
import ariel.livros.dto.students.StudentResponse;
import ariel.livros.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl service;

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody @Valid StudentRequest request, UriComponentsBuilder uriBuilder) {
        StudentResponse response = service.create(request);

        var uri = uriBuilder.path("/student/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
