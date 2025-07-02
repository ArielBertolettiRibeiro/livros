package ariel.livros.controllers;

import ariel.livros.dto.loans.LoanRequest;
import ariel.livros.dto.loans.LoanResponse;
import ariel.livros.dto.loans.LoanSummary;
import ariel.livros.service.impl.LoanServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("loan")
@RestController
public class LoanController {

    private final LoanServiceImpl service;

    @PostMapping
    public ResponseEntity<LoanResponse> create(@RequestBody @Valid LoanRequest request, UriComponentsBuilder uriBuilder) {
        LoanResponse response = service.create(request);

        var uri = uriBuilder.path("/loan/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Void> returnLoan(@PathVariable Long id) {
        service.returnLoan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{id}")
    public  ResponseEntity<List<LoanSummary>> findAll(@PathVariable Long id) {
        List<LoanSummary> loans = service.findAll(id);
        return ResponseEntity.ok(loans);
    }
}
