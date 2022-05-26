package fincons.todo.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fincons.todo.backend.entities.dto.TodoDto;
import fincons.todo.backend.services.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

	private TodoService todoService;
	
	public TodoController(
			@Qualifier("todoServiceImpl") TodoService todoService
			) {
		this.todoService = todoService;
	}
	
	@GetMapping("/user/{user_id}")
	public ResponseEntity<List<TodoDto>> getTodosByUser(@PathVariable("user_id") Long userId) {
		List<TodoDto> todoDtoList = todoService.findTodoByUserId(userId);
		if (todoDtoList == null) return new ResponseEntity<List<TodoDto>>(HttpStatus.NO_CONTENT);
		else return new ResponseEntity<List<TodoDto>>(todoDtoList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Long> create(@RequestBody TodoDto todoDto) {
		Long id = todoService.create(todoDto);
		return id != null 
				? new ResponseEntity<Long>(id, HttpStatus.OK) : new ResponseEntity<Long>(HttpStatus.NO_CONTENT);
	}

}
