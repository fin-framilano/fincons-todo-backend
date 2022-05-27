package fincons.todo.backend.repositories.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import fincons.todo.backend.entities.Todo;
import fincons.todo.backend.repositories.TodoRepository;

/**
 * Implementazione in memory del repository dei Todo
 * @author francesco
 */

@Repository
public class TodoInMemoryRepository implements TodoRepository {
	
	/**
	 * Come creo un'implementazione in-memory di una JPA Repository?
	 * Ho scelto di riemplementare a mano i metodi utilizzati all'interno dell'applicazione
	 * In modo tale da permettere un cambio minimo nel codice dei Service (solo il Qualifier)
	 * e permettere il funzionamento anche con un'implementazione senza DB
	 * 
	 * Sono costretto a includere/implementare anche i metodi che non utilizzo della JPA Repository
	 * Sicuramente esiste un modo migliore per fare ciò
	 */
	
	private List<Todo> listOfTodos = new ArrayList<Todo>();
	
	private Long currentIndex = Long.valueOf(0);

	public List<Todo> findTodoByUserId(Long user_id) {
		List<Todo> todosByUserId = new ArrayList<Todo>();
		for (Todo t: this.listOfTodos) {
			if (t.getUser().getId() == user_id) todosByUserId.add(t);
		}
		return todosByUserId;
	}
	
	public <T extends Todo> T save(T todo) {
		todo.setId(this.currentIndex += 1);
		this.listOfTodos.add(todo);
		return todo;
	}
	
	public void delete(Todo todo) {
		for (Todo t: this.listOfTodos) {
			if (t.getId() == todo.getId()) {
				this.listOfTodos.remove(todo);
				break;
			}
		}
	}
	
	public Optional<Todo> findById(Long id) {
		for (Todo t: this.listOfTodos) {
			if (t.getId() == id) {
				return Optional.of(t);
			}
		}
		return null;
	}
	
	public <S extends Todo> S saveAndFlush(S entity) {
		//Elimino la nota precedente e la sostituisco con la nuova
		this.delete(this.findById(entity.getId()).get());
		this.listOfTodos.add(entity);
		return entity;
	}
	
	/**
	 * Da qui i metodi necessari per l'implementazione di TodoRepository
	 */

	@Override
	public List<Todo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Todo> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Todo> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Todo> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Todo> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Todo> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Todo getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Todo getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Todo getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Todo> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Todo> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Todo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Todo> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Todo> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Todo> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Todo> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Todo> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Todo, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

}
