package fincons.todo.backend.services;

import java.util.List;

import fincons.todo.backend.entities.dtos.TodoDto;

public interface TodoService {

	List<TodoDto> findTodoByUserId(Long user_id);

	Long create(TodoDto todoDto) throws IllegalArgumentException;

	Long update(Long id, TodoDto todoDto) throws IllegalArgumentException;

	void delete(Long id);


}
