package ePlattform.server.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.services.NutzerService;

@RestController
@RequestMapping("/nutzer")
public class NutzerController {
	
	private NutzerService service;

	public NutzerController(NutzerService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/register")
	boolean registerNutzer(@RequestBody Nutzer newNutzer) {
		return service.registerNutzer(newNutzer);
	}
	
	@GetMapping("/login")
	Nutzer loginNutzer(
			@RequestParam("benutzername") String benutzername,
			@RequestParam("passwort") String passwort) {
		Nutzer loggingNutzer = service.loginNutzer(benutzername, passwort);
		return loggingNutzer;
	}
	

	
	
}
