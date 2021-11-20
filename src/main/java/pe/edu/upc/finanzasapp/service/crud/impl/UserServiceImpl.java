package pe.edu.upc.finanzasapp.service.crud.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.finanzasapp.model.entity.User;
import pe.edu.upc.finanzasapp.model.repository.UserRepository;
import pe.edu.upc.finanzasapp.service.crud.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public JpaRepository<User, Integer> getRepository() {
		
		return userRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

}
