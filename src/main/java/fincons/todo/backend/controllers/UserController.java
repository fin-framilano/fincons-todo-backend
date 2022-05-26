package fincons.todo.backend.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fincons.todo.backend.entities.dto.UserDto;
import fincons.todo.backend.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;
	
	public UserController(
			@Qualifier("userServiceImpl") UserService userService 
	) {
		this.userService = userService;
	}
	
	@GetMapping("/{mail}")
	public ResponseEntity<UserDto> findByMail(@PathVariable("mail") String mail) {
		UserDto userDto = userService.findByMail(mail);
		return userDto != null 
				? new ResponseEntity<UserDto>(userDto, HttpStatus.OK) : new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<Long> create(@RequestBody UserDto userDto) {
		Long id = userService.create(userDto);
		return id != null 
				? new ResponseEntity<Long>(id, HttpStatus.OK) : new ResponseEntity<Long>(HttpStatus.NO_CONTENT);
	}
}
