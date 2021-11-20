package pe.edu.upc.finanzasapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.finanzasapp.model.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
