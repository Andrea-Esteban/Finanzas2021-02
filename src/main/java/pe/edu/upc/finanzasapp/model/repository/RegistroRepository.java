package pe.edu.upc.finanzasapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import pe.edu.upc.finanzasapp.model.entity.Registro;


@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer>{

}
