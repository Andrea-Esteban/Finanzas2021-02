package pe.edu.upc.finanzasapp.service.crud;



import java.util.List;

import pe.edu.upc.finanzasapp.model.entity.Descuento;
import pe.edu.upc.finanzasapp.model.entity.Registro;

public interface DescuentoService extends CrudService<Descuento, Integer>{
	List<Descuento> findByRegistro(Registro registro)throws Exception;
}
