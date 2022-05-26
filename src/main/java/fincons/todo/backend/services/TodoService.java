package fincons.todo.backend.services;

import java.util.List;

import fincons.todo.backend.entities.dto.TodoDto;

public interface TodoService {

	List<TodoDto> findTodoByUserId(Long user_id);

	Long create(TodoDto todoDto);

}
