package pe.edu.upc.finanzasapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.finanzasapp.model.entity.Descuento;
import pe.edu.upc.finanzasapp.model.entity.Registro;
import pe.edu.upc.finanzasapp.service.crud.DescuentoService;
import pe.edu.upc.finanzasapp.service.crud.RegistroService;

@Controller
@RequestMapping("/descuentos")
public class DescuentoController {
	
	@Autowired
	private DescuentoService descuentoService;
	
	@Autowired
	private RegistroService registroService;
	
	@GetMapping
	public String listar(Model model) {
		try {
			List<Descuento> descuentos = descuentoService.getAll();
			model.addAttribute("descuentos", descuentos);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "descuentos/lista";
	}
	
	@GetMapping("new")
	public String newItem(Model model) {
		try {
			Descuento descuento = new Descuento();
			model.addAttribute("descuentoNew", descuento);
			List<Registro> registros = registroService.getAll();
			model.addAttribute("registros",registros);
			return "descuentos/new";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/descuentos";
	}
	
	@PostMapping("savenew")
	public String saveNew(Model model, @ModelAttribute("descuentoNew") Descuento descuento ,SessionStatus status, RedirectAttributes attributes) {
		try {
			if(descuento.getTipoTasa() == "") {
				System.out.println("Le gusto.");
				attributes.addFlashAttribute("mensaje", "Seleccione un tipo de tasa, porfavor.");
				return "redirect:/descuentos/new";
			}
			else if(descuento.getTasa() <= 0) {
				System.out.println("No le gusto.");
				attributes.addFlashAttribute("mensaje", "El porcentaje de la tasa debe ser mayor a 0.");
				return "redirect:/descuentos/new";
			}
			else if(descuento.getGastosIniciales() <0){
				System.out.println("Le gusto a medias.");
				attributes.addFlashAttribute("mensaje", "Ingrese gastos iniciales válidos, porfavor.");
				return "redirect:/descuentos/new";
			}
			else {
				if(descuento.getTipoTasa().contentEquals("Nominal") ) {
					System.out.println("Es linda.");
					
					
					System.out.println("Aqui");
					double TEAaux;
					double TEA;
					
					
					double ValorNominal = descuento.getRegistro().getMontoTotal(); //Dato
					double gastosIniciales = descuento.getGastosIniciales(); //dato
					double gastosFinales = descuento.getGastosFinales();//dato;
					double Efectiva2;
					double exponencial;
					double d;
					double descuentoAUX;
					double valorNeto;
					double valorRecibido;
					double valorEntregado;
					double exponencialTCEA;
					double tcea;
					
					TEAaux = (double)((double)descuento.getTasa()/100)/360;
					TEA = (double) Math.pow((1+TEAaux), 360)-1;
					
					exponencial = (double)descuento.getRegistro().getDias()/360;
					
					
					Efectiva2 = (Math.pow(((double)TEA +1 ), (double) exponencial))-1;
					
					d=  (double)Efectiva2/(1+Efectiva2);
					
					descuentoAUX = (double)ValorNominal * d;
					
					valorNeto = (double) ValorNominal - descuentoAUX;
					
					valorRecibido = valorNeto - gastosIniciales;
					
					valorEntregado = ValorNominal + gastosFinales;
					
					exponencialTCEA = (double) 360/descuento.getRegistro().getDias();
					tcea = Math.pow((double)(valorEntregado/valorRecibido), exponencialTCEA)-1;
					
					descuento.setD(d*100);
					descuento.setDescuento(descuentoAUX);
					descuento.setValorNeto(valorNeto);
					descuento.setValorRecibido(valorRecibido);
					descuento.setValorEntregado(valorEntregado);
					descuento.setTcea(tcea*100);
					Descuento descuentoReturn = descuentoService.create(descuento);
					model.addAttribute("descuento", descuentoReturn);
					return "descuentos/view";
					
										
					
					
				}
				else if(descuento.getTipoTasa().contentEquals("Efectiva")) {
					System.out.println("Aqui");
					//int diasTEC = 120; //dato
					double TEAEjemplo = (double)descuento.getTasa()/100; //dato
					double ValorNominal = descuento.getRegistro().getMontoTotal(); //Dato
					double gastosIniciales = descuento.getGastosIniciales(); //dato
					double gastosFinales = descuento.getGastosFinales();//dato;
					double Efectiva2;
					double exponencial;
					double d;
					double descuentoAUX;
					double valorNeto;
					double valorRecibido;
					double valorEntregado;
					double exponencialTCEA;
					double tcea;
					exponencial = (double)descuento.getRegistro().getDias()/360;
					
					Efectiva2 = (Math.pow(((double)TEAEjemplo +1 ), (double) exponencial))-1;
					
					d=  (double)Efectiva2/(1+Efectiva2);
					
					descuentoAUX = (double)ValorNominal * d;
					
					valorNeto = (double) ValorNominal - descuentoAUX;
					
					valorRecibido = valorNeto - gastosIniciales;
					
					valorEntregado = ValorNominal + gastosFinales;
					
					exponencialTCEA = (double) 360/descuento.getRegistro().getDias();
					tcea = Math.pow((double)(valorEntregado/valorRecibido), exponencialTCEA)-1;
					
					descuento.setD(d*100);
					descuento.setDescuento(descuentoAUX);
					descuento.setValorNeto(valorNeto);
					descuento.setValorRecibido(valorRecibido);
					descuento.setValorEntregado(valorEntregado);
					descuento.setTcea(tcea*100);
					Descuento descuentoReturn = descuentoService.create(descuento);
					model.addAttribute("descuento", descuentoReturn);
					return "descuentos/view";
				}
				else if(descuento.getTipoTasa()=="De Descuento") {
					System.out.println("10/10 - 10 /10");
					
					double ValorNominal = descuento.getRegistro().getMontoTotal(); //Dato
					double gastosIniciales = descuento.getGastosIniciales(); //dato
					double gastosFinales = descuento.getGastosFinales();//dato;
					
					double d = (double)descuento.getD() /100;
					double descuentoAUX;
					double valorNeto;
					double valorRecibido;
					double valorEntregado;
					double exponencialTCEA;
					double tcea;
					
					System.out.println(d);
					descuentoAUX = (double)ValorNominal * d;
					
					valorNeto = (double) ValorNominal - descuentoAUX;
					
					valorRecibido = valorNeto - gastosIniciales;
					
					valorEntregado = ValorNominal + gastosFinales;
					
					exponencialTCEA = (double) 360/descuento.getRegistro().getDias();
					tcea = Math.pow((double)(valorEntregado/valorRecibido), exponencialTCEA)-1;
					
					descuento.setD(d);
					descuento.setDescuento(descuentoAUX);
					descuento.setValorNeto(valorNeto);
					descuento.setValorRecibido(valorRecibido);
					descuento.setValorEntregado(valorEntregado);
					descuento.setTcea(tcea*100);
					Descuento descuentoReturn = descuentoService.create(descuento);
					model.addAttribute("descuento", descuentoReturn);
					return "descuentos/view";
					
					
					
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/descuentos";
	}
	
	@GetMapping("del/{id}")
	public String del(@PathVariable("id") Integer id, Model model,SessionStatus status,
			RedirectAttributes attributes) {
		try {
			descuentoService.deleteById(id);
			attributes.addFlashAttribute("mensajeSuccess","Se eliminó el descuento correctamente.");
			return "redirect:/descuentos";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/descuentos";
	}
}
