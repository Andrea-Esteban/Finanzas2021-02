package pe.edu.upc.finanzasapp.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.finanzasapp.model.entity.Registro;
import pe.edu.upc.finanzasapp.model.repository.RegistroRepository;
import pe.edu.upc.finanzasapp.service.crud.RegistroService;

@Service
public class RegistroServiceImpl implements RegistroService{

	@Autowired
	private RegistroRepository registroRepository;
	
	@Override
	public JpaRepository<Registro, Integer> getRepository() {
		return registroRepository;
	}

}
