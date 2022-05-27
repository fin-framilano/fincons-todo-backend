package fincons.todo.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fincons.todo.backend.entities.dto.LoginDto;
import fincons.todo.backend.entities.dto.UserDto;
import fincons.todo.backend.services.SecurityService;

/**
 * Controller di sicurezza utilizzato essenzialmente per il login
 * Ma dovrebbe contenere anche funzione di autenticazione aggiuntive relative alla gestione
 * del JWS Token e interazione con gli interceptors
 * 
 * Esposizione di una full REST API di questo applicativo
 * @author francesco
 *
 */

@RestController
@RequestMapping("/security")
public class SecurityController {
	
	private static Logger logger = LoggerFactory.getLogger(SecurityController.class);

	private SecurityService securityService;
	
	public SecurityController(
			@Qualifier("securityServiceImpl") SecurityService securityService
			) {
		this.securityService = securityService;
	}
	
	@PostMapping
	public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {
		//Logging
		logger.info("POST-(/security)-SecurityController: Richiesta di login");
		
		UserDto userDto = securityService.findUserByMailAndPassword(loginDto);
		if (userDto == null) return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
		else return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	

}
