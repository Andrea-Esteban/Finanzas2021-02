package pe.edu.upc.finanzasapp.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.finanzasapp.model.entity.Customer;
import pe.edu.upc.finanzasapp.model.entity.Moneda;
import pe.edu.upc.finanzasapp.model.entity.User;
import pe.edu.upc.finanzasapp.model.repository.CustomerRepository;
import pe.edu.upc.finanzasapp.model.repository.MonedaRepository;
import pe.edu.upc.finanzasapp.model.repository.UserRepository;
import pe.edu.upc.finanzasapp.service.crud.MonedaService;
import pe.edu.upc.finanzasapp.service.crud.UserService;

@Service
public class InitDB implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MonedaRepository monedaRepository;
	
	@Autowired
	private MonedaService monedaService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(userService.findByUsername("Login").isPresent()) {
			System.out.println("¡Bienvenido de nuevo!");
			
		}
		else {
			System.out.println("Si vivimos.");
			Moneda soles = new Moneda();
			Moneda dolares = new Moneda();
			
			soles.setId(1);
			soles.setNombre("Soles");
			dolares.setId(2);
			dolares.setNombre("Dólares");
			monedaRepository.save(soles);
			monedaRepository.save(dolares);
			
						
			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			Customer customerAdmin = new Customer();
			customerAdmin.setId(1);
			customerAdmin.setFirstname("Administrador");
			customerAdmin.setLastname("FinanzasApp");
			customerAdmin.setEmail("administrador@appfinanzas.com");
			
			User admin = new User();
			admin.setCustomer(customerAdmin);
			customerAdmin.setUser(admin);
			admin.setId(1);
			admin.setUsername("Login");
			admin.setPassword(bcpe.encode("login"));
			admin.setEnable(true);
			admin.addAuthority("ROLE_ADMIN");
			userRepository.save(admin);
			System.out.println("Todo listo. Puedes empezar a utilizar FinanzasApp");
		}
		
		
		
		
		
		
		
		
		
	}

}
