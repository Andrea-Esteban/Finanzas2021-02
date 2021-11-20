package pe.edu.upc.finanzasapp.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.finanzasapp.model.entity.Moneda;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, Integer> {
	Optional<Moneda> findByNombre(String nombre);
}
