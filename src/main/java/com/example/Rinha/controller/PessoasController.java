package com.example.Rinha.controller;

import com.example.Rinha.Dto.PessoaDto;
import com.example.Rinha.model.Pessoa;
import com.example.Rinha.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PessoasController {
    @Autowired
    PessoaRepository pessoaRepository;

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> adicionarPessoa( @Valid @RequestBody PessoaDto pessoaDto) {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(UUID.randomUUID());
        pessoa.setApelido(pessoaDto.apelido());
        pessoa.setNome(pessoaDto.nome());
        pessoa.setNascimento(pessoaDto.nascimento());
        pessoa.setStack(pessoaDto.stack());

        Pessoa pessoa1 = pessoaRepository.save(pessoa);
        return ResponseEntity.ok(pessoa1);
    }


    @GetMapping("/{id}")  // Mapeia o método para responder a GET /pessoas/{id}
    public ResponseEntity<Pessoa> getPessoaPorId(@PathVariable UUID id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> buscarPessoasPorTermo(@RequestParam(name = "t") String termo) {
        List<Pessoa> pessoasEncontradas = pessoaRepository.findByNomeOrApelidoContaining(termo);

        if (pessoasEncontradas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pessoasEncontradas);
    }

    @GetMapping("/contagem-pessoas")

        public long contarPessoasCadastradas() {
        return pessoaRepository.count();
        }
    }


