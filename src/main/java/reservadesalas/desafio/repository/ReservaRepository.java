package reservadesalas.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import reservadesalas.desafio.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
