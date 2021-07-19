package todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.model.TodoDTO;

@Repository
public interface TodoRepository extends JpaRepository<TodoDTO, Long> {
}
