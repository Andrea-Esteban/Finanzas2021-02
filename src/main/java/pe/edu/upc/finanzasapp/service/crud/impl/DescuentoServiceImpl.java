package pe.edu.upc.finanzasapp.service.crud.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.finanzasapp.model.entity.Descuento;
import pe.edu.upc.finanzasapp.model.entity.Registro;
import pe.edu.upc.finanzasapp.model.repository.DescuentoRepository;
import pe.edu.upc.finanzasapp.service.crud.DescuentoService;
@Service
public class DescuentoServiceImpl implements DescuentoService{

	@Autowired
	private DescuentoRepository descuentoRepository;
	
	@Override
	public JpaRepository<Descuento, Integer> getRepository() {
		
		return descuentoRepository;
	}

	@Override
	public List<Descuento> findByRegistro(Registro registro) throws Exception {
		
		return descuentoRepository.findByRegistro(registro);
	}

	

}
