package reservadesalas.desafio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import reservadesalas.desafio.enums.StatusReserva;
import reservadesalas.desafio.model.Reserva;
import reservadesalas.desafio.model.Sala;
import reservadesalas.desafio.repository.ReservaRepository;
import reservadesalas.desafio.repository.SalaRepository;

@RestController
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;

    @PostMapping("/reservas")
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva) {
        Sala sala = salaRepository.findById(reserva.getSala().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada"));
        reserva.setSala(sala);
        reserva.setStatus(StatusReserva.ATIVA);
        Reserva salva = reservaRepository.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping("/reservas/{id}")
    public Reserva consultar(@PathVariable Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));
    }

    @GetMapping("/salas/{id}/reservas")
    public List<Reserva> listarPorSala(@PathVariable Long id) {
        salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada"));
        return reservaRepository.findAll().stream()
                .filter(reserva -> reserva.getSala().getId().equals(id))
                .toList();
    }

    @PatchMapping("/reservas/{id}/cancelamento")
    public Reserva cancelar(@PathVariable Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));
        reserva.setStatus(StatusReserva.CANCELADA);
        return reservaRepository.save(reserva);
    }
}
