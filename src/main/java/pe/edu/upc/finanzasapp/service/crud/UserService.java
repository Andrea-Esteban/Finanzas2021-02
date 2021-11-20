package pe.edu.upc.finanzasapp.service.crud;

import java.util.Optional;

import pe.edu.upc.finanzasapp.model.entity.User;

public interface UserService extends CrudService<User, Integer>{
	Optional<User> findByUsername(String username) throws Exception;
}
