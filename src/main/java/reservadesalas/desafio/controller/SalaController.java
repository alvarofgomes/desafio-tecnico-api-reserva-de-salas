package reservadesalas.desafio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reservadesalas.desafio.model.Sala;
import reservadesalas.desafio.repository.SalaRepository;

@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaRepository salaRepository;

    @PostMapping
    public ResponseEntity<Sala> criar(@RequestBody Sala sala) {
        Sala salva = salaRepository.save(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public List<Sala> listar() {
        return salaRepository.findAll();
    }
}
