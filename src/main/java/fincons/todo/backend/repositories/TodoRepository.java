package fincons.todo.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fincons.todo.backend.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	@Query(name = "select t from Todo t where t.user_id = :user_id", nativeQuery = true)
	public List<Todo> findTodoByUserId(Long user_id);

}
