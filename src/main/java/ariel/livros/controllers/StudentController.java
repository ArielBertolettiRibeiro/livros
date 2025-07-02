package ariel.livros.controllers;

import ariel.livros.dto.students.StudentRequest;
import ariel.livros.dto.students.StudentResponse;
import ariel.livros.dto.students.StudentSummary;
import ariel.livros.dto.students.StudentUpdateRequest;
import ariel.livros.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<StudentSummary>> findAll(@PageableDefault(size = 10, sort = "name")Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @RequestBody @Valid StudentUpdateRequest student) {
        return ResponseEntity.ok(service.update(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
