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

	/**
	 * Constructor Injection dei repository, i qualifier disponibili sono:
	 * nameRepository per la JPA Repository con DB Postgres
	 * nameInMemoryRepository per l'implementazione in memory tramite ArrayList
	 * @param todoRepository
	 * @param userRepository
	 */
	
	public TodoServiceImpl(
			//@Qualifier("todoInMemoryRepository") TodoRepository todoRepository,
			//@Qualifier("userInMemoryRepository") UserRepository userRepository
			@Qualifier("todoRepository") TodoRepository todoRepository,
			@Qualifier("userRepository") UserRepository userRepository) {
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
	}
	
	/**
	 * Recupera tutti i promemoria collegati a un particolare ID Utente
	 */
	public List<TodoDto> findTodoByUserId(Long userId) {
		List<Todo> todoVoList = this.todoRepository.findTodoByUserId(userId);
		if (todoVoList == null)
			return null;
		//Mapping da Value Object a DATA Transfer Object
		List<TodoDto> todoDtoList = todoVoList.stream().map(t -> TodoUtils.fromVOtoDTO(t)).collect(Collectors.toList());
		return todoDtoList;
	}

	/**
	 * Variabile di fallback nel caso nessuna data di scadenza sia inserita
	 * Il valore di questa variabile è recuperato da application.properties
	 */
	@Value("${days.fallback}")
	private int days_fallback;

	
	/**
	 * Crea un nuovo utente
	 */
	@SuppressWarnings("deprecation")
	public Long create(TodoDto todoDto) {
		todoDto.setId(Long.valueOf(0));
		//Creo un VO dal DTO dell'utente
		Todo todoVo = TodoUtils.fromDTOtoVO(todoDto);
		
		// Gestisco la creazione dei timestamp in JAVA, nel front-end (il dto) faceva uso di semplici stringhe
		// per facilitare la conversione da un formato ad un altro
		Timestamp createdAt = Timestamp.valueOf(todoDto.getCreatedAt());
		Timestamp dueDate = Timestamp.valueOf(todoDto.getDueDate());
		
		// La data "nulla" quando il form della data non è riempito è 1970-01-01 01:00
		// Se la data effettiva è quella, allora inserisco come dueDate la data attuale + days_fallback giorni
		// Utilizzo Calendar per facilitare la somma dei giorni
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

	/**
	 * Cancello il promemoria collegato a un particolare ID
	 */
	public void delete(Long id) {
		Todo todoVo = this.todoRepository.findById(id).get();
		if (todoVo != null) this.todoRepository.delete(todoVo);		
	}

	
	/**
	 * Metodo di aggiornamento di un promemoria
	 */
	@SuppressWarnings("deprecation")
	public Long update(Long id, TodoDto todoDto) {
		todoDto.setId(id);
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
		return this.todoRepository.saveAndFlush(todoVo).getId();
	}
	
	

}
