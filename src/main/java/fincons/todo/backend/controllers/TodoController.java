package fincons.todo.backend.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fincons.todo.backend.entities.dtos.TodoDto;
import fincons.todo.backend.services.TodoService;

/**
 * Controller che espone le API Rest relative ai promemoria
 * 
 * @author francesco
 *
 */

@RestController
@RequestMapping("/todos")
public class TodoController {

	private static Logger logger = LoggerFactory.getLogger(TodoController.class);
	
	private TodoService todoService;

	/**
	 * Constructor Injection del servizio collegato alla gestione dei promemoria
	 * 
	 * @param todoService
	 */
	public TodoController(@Qualifier("todoServiceImpl") TodoService todoService) {
		this.todoService = todoService;
	}

	// Restituisce tutti i Todo collegati a un utente
	@GetMapping("/user/{user_id}")
	public ResponseEntity<List<TodoDto>> getTodosByUser(@PathVariable("user_id") Long userId) {
		//Logging
		logger.info("GET-(/todos/user/{user_id})-TodoController: Richiesta dei promemoria collegati a un utente");

		List<TodoDto> todoDtoList = todoService.findTodoByUserId(userId);
		if (todoDtoList == null)
			return new ResponseEntity<List<TodoDto>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<TodoDto>>(todoDtoList, HttpStatus.OK);
	}

	// Creazione di un nuovo Todo
	@PostMapping
	public ResponseEntity<Long> create(@RequestBody TodoDto todoDto) {
		//Logging
		logger.info("POST-(/todos)-TodoController: Richiesta creazione di un Todo");
		
		try {
			Long id = todoService.create(todoDto);	
			return id != null ? new ResponseEntity<Long>(id, HttpStatus.OK)
					: new ResponseEntity<Long>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
		
	}

	// Creazione di un nuovo Todo
	@PutMapping("/{id}")
	public ResponseEntity<Long> update(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto) {
		//Logging
		logger.info("PUT-(/todos/{id})-TodoController: Richiesto aggiornamento di un Todo");
		
		try {
			Long id = todoService.update(todoId, todoDto);
			return id != null ? new ResponseEntity<Long>(id, HttpStatus.OK)
					: new ResponseEntity<Long>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
		
	}

	// Eliminazione di un Todo dato il suo id
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		logger.info("DELETE-(/todos/{id})-TodoController: Richiesta eliminazione di un Todo");
		this.todoService.delete(id);
	}

}
