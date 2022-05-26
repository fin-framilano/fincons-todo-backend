package fincons.todo.backend.utils;

import fincons.todo.backend.entities.Todo;
import fincons.todo.backend.entities.dto.TodoDto;

/**
 * Le classi Utils mi servono essenzialmente per convertire un Vo in un DTO e viceversa
 * Due metodi sono implementati per questo scopo
 * @author francesco
 *
 */

public class TodoUtils {

	public static TodoDto fromVOtoDTO(Todo t) {
		TodoDto todoDto = new TodoDto();
		
		todoDto.setContent(t.getContent());
		todoDto.setCreatedAt(t.getCreatedAt().toString().replace(":00.0", ""));
		todoDto.setDueDate(t.getDueDate().toString().replace(":00.0", ""));
		todoDto.setId(t.getId());
		todoDto.setStatus(t.getStatus());
		todoDto.setUserId(t.getUser().getId());
		
		return todoDto;
	}
	
	public static Todo fromDTOtoVO(TodoDto t) {
		Todo todo = new Todo();
		
		todo.setContent(t.getContent());
		todo.setId(t.getId());
		todo.setStatus(t.getStatus());
		
		return todo;
	}

}
