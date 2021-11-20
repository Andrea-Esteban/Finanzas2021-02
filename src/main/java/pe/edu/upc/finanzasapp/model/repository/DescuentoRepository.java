package pe.edu.upc.finanzasapp.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.finanzasapp.model.entity.Descuento;
import pe.edu.upc.finanzasapp.model.entity.Registro;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Integer>{
	List<Descuento>findByRegistro(Registro registro);
}
