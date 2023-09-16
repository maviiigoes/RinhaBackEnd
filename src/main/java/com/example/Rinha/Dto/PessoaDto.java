package com.example.Rinha.Dto;

import jakarta.validation.constraints.*;

import java.util.List;
import java.util.UUID;

public record PessoaDto(

        @NotNull
        @NotEmpty
        @Size(min = 1, max = 32)
        String apelido,

        @NotNull
        @NotEmpty
        @Size(min = 1, max = 100)
        String nome,

        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "A data de nascimento deve estar no formato AAAA-MM-DD")
        String nascimento,


        List<@Size(min=1,max=32)String  > stack
) {
}
