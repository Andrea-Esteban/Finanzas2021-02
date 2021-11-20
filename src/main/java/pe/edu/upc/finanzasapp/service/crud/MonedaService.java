package pe.edu.upc.finanzasapp.service.crud;

import java.util.Optional;

import pe.edu.upc.finanzasapp.model.entity.Moneda;

public interface MonedaService extends CrudService<Moneda, Integer>{
	Optional<Moneda> findByNombre(String nombre)throws Exception;
}
