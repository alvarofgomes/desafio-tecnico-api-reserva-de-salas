package reservadesalas.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import reservadesalas.desafio.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {
}
