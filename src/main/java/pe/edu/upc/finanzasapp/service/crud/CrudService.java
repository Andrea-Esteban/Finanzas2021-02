package pe.edu.upc.finanzasapp.service.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CrudService<T,ID> {
	
	JpaRepository<T, ID> getRepository();
	
	@Transactional(readOnly = true)
	default List<T> getAll() throws Exception{
		return getRepository().findAll();// Obtener todos los elementos
	}
	
	@Transactional(readOnly = true)
	default Optional<T> findById(ID id) throws Exception{
		return getRepository().findById(id); //bUSCAR POR iD
	}
	
	@Transactional
	default T create(T entity) throws Exception{
		return getRepository().save(entity);//Persiste el entity en BD (Repository->BD)
	}
	
	@Transactional
	default T update(T entity) throws Exception{
		return getRepository().save(entity);//Actualiza el entity (Repository->BD)
	}
	
	@Transactional
	default void deleteById(ID id) throws Exception {
		getRepository().deleteById(id);//Elimina el ENTITY (Repository->BD)
	}
}
