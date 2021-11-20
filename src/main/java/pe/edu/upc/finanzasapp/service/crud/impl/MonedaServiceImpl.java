package pe.edu.upc.finanzasapp.service.crud.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.finanzasapp.model.entity.Moneda;
import pe.edu.upc.finanzasapp.model.repository.MonedaRepository;
import pe.edu.upc.finanzasapp.service.crud.MonedaService;

@Service
public class MonedaServiceImpl implements MonedaService{

	@Autowired
	private MonedaRepository monedaRepository;
	
	@Override
	public JpaRepository<Moneda, Integer> getRepository() {
		
		return monedaRepository;
	}

	@Override
	public Optional<Moneda> findByNombre(String nombre) throws Exception {
		
		return monedaRepository.findByNombre(nombre);
	}

}
