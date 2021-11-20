package pe.edu.upc.finanzasapp.controller;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.finanzasapp.model.entity.Moneda;
import pe.edu.upc.finanzasapp.model.entity.Registro;
import pe.edu.upc.finanzasapp.service.crud.DescuentoService;
import pe.edu.upc.finanzasapp.service.crud.MonedaService;
import pe.edu.upc.finanzasapp.service.crud.RegistroService;

@Controller
@RequestMapping("/registros")
@SessionAttributes("registrosEdit")
public class RegistroController {

	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private MonedaService monedaService;
	
	@Autowired
	private DescuentoService descuentoService;
	
	
	@GetMapping
	public String listar(Model model) {
		try {
			List<Registro> registros = registroService.getAll();
			model.addAttribute("registros", registros);
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "registros/lista";
	}
	
	@GetMapping("new")
	public String newItem(Model model) {
		try {
			Registro registro = new Registro();
			model.addAttribute("registroNew", registro);
			List<Moneda> monedas = monedaService.getAll();
			model.addAttribute("monedas",monedas);
			return "registros/new";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "redirect:/registros";
	}
	
	@PostMapping("savenew")
	public String saveNew(Model model, @ModelAttribute("registroNew") Registro registro, SessionStatus status, RedirectAttributes attributes) {
		try {
			
			if(registro.getNombreEmpresa().isBlank()||registro.getNombreEmpresa().isEmpty()) {
				attributes.addFlashAttribute("mensaje", "Ingrese un nombre de empresa, por favor.");
				return "redirect:/registros/new";
			}
			else if (registro.getMontoTotal() <=0) {
				attributes.addFlashAttribute("mensaje", "Ingrese un monto mayor a 0, por favor.");
				return "redirect:/registros/new";
			}
			else if(registro.getFechainicio() ==null) {
				System.out.println("Prefiere a los Geas.");
				attributes.addFlashAttribute("mensaje", "La fecha final o inicial no pueden ser nulas. Ingresa un rango de fecha válidas.");
				return "redirect:/registros/new";
			}
			else if(registro.getFechafinal()==null) {
				System.out.println("Prefiere mi amistad.");
				attributes.addFlashAttribute("mensaje", "La fecha final o inicial no pueden ser nulas. Ingresa un rango de fecha válidas.");
				return "redirect:/registros/new";
			}
			else if(registro.getFechainicio().after(registro.getFechafinal())) {
				System.out.println("Le importo un carajo.");
				attributes.addFlashAttribute("mensaje", "La fecha final no puede ser antes de la fecha inicial. Ingrese un rango de fechas válidas.");
				return "redirect:/registros/new";
			}
			else if(registro.getFechainicio().equals(registro.getFechafinal())) {
				System.out.println("Me hace caso.");
				attributes.addFlashAttribute("mensaje", "La fecha final e inicial no pueden ser el mismo día. Ingresa un rango de fecha válidas.");
				return "redirect:/registros/new";
			}
			
			else {
				long diff = registro.getFechafinal().getTime() - registro.getFechainicio().getTime();
				TimeUnit time = TimeUnit.DAYS;
				long difference = time.convert(diff, TimeUnit.MILLISECONDS);
				System.out.println("La diferencia es de " + difference);
				registro.setDias(difference);
				Registro registroReturn = registroService.create(registro);
				model.addAttribute("registro", registroReturn);
				return "registros/view";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/registros";
	}
	
	@GetMapping("del/{id}")
	public String del(@PathVariable("id") Integer id, Model model,SessionStatus status,
			RedirectAttributes attributes) {
		try {
			Optional<Registro> optional = registroService.findById(id);
			if(optional.isPresent()) {
				if(descuentoService.findByRegistro(registroService.findById(id).get()).size()>=1) {
					attributes.addFlashAttribute("mensajeError","Error al borrar un registro. El registro tiene descuentos registrados en la base de datos.");
					return "redirect:/registros";
				}
				else {
					registroService.deleteById(id);
					attributes.addFlashAttribute("mensajeSuccess","Se eliminó el registro correctamente.");
					return "redirect:/registros";
				}
			}
			else {
				return "redirect:/registros";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "redirect:/registros";
	}
	
}
