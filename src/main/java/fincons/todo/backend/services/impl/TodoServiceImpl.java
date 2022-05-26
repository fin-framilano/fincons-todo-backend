package fincons.todo.backend.services.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fincons.todo.backend.entities.Todo;
import fincons.todo.backend.entities.dto.TodoDto;
import fincons.todo.backend.repositories.TodoRepository;
import fincons.todo.backend.repositories.UserRepository;
import fincons.todo.backend.services.TodoService;
import fincons.todo.backend.utils.TodoUtils;

@Service
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;
	private UserRepository userRepository;

	public TodoServiceImpl(@Qualifier("todoRepository") TodoRepository todoRepository,
			@Qualifier("userRepository") UserRepository userRepository) {
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
	}

	public List<TodoDto> findTodoByUserId(Long userId) {
		List<Todo> todoVoList = this.todoRepository.findTodoByUserId(userId);
		if (todoVoList == null)
			return null;
		List<TodoDto> todoDtoList = todoVoList.stream().map(t -> TodoUtils.fromVOtoDTO(t)).collect(Collectors.toList());
		return todoDtoList;
	}

	/**
	 * Variabile di fallback nel caso nessuna data di scadenza sia inserita
	 */
	@Value("${days.fallback}")
	private int days_fallback;

	@SuppressWarnings("deprecation")
	public Long create(TodoDto todoDto) {
		todoDto.setId(Long.valueOf(0));
		Todo todoVo = TodoUtils.fromDTOtoVO(todoDto);
		Timestamp createdAt = Timestamp.valueOf(todoDto.getCreatedAt());
		Timestamp dueDate = Timestamp.valueOf(todoDto.getDueDate());
		if (dueDate.getYear() == 70) {
			System.out.println("Data di fallback, prendo la data attuale e aggiungo " + days_fallback + " giorni");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(createdAt);
			calendar.add(Calendar.DAY_OF_MONTH, this.days_fallback);
			Timestamp newDueDate = new Timestamp(calendar.getTimeInMillis());
			todoVo.setDueDate(newDueDate);
			todoVo.setCreatedAt(createdAt);
		} else {
			todoVo.setDueDate(dueDate);
			todoVo.setCreatedAt(createdAt);
		}
		todoVo.setUser(this.userRepository.findById(todoDto.getUserId()).get());
		return this.todoRepository.save(todoVo).getId();
	}

	@Override
	public void delete(Long id) {
		Todo todoVo = this.todoRepository.findById(id).get();
		if (todoVo != null) this.todoRepository.delete(todoVo);		
	}
	
	

}
