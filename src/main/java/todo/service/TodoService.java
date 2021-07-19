package todo.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import todo.model.RequestDTO;
import todo.model.TodoDTO;
import todo.repository.TodoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // Create
    public TodoDTO add(RequestDTO request) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setContent(request.getContent());
        todoDTO.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoDTO);
    }

    // search by id
    public TodoDTO findById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // search All
    public List<TodoDTO> findAll() {
        return this.todoRepository.findAll();
    }

    // update
    public TodoDTO updateById(Long id, RequestDTO request) {
        TodoDTO todoDTO = this.findById(id);

        if(request.getContent() != null) {
            todoDTO.setContent(request.getContent());
        }
        if(request.getCompleted() != null) {
            todoDTO.setCompleted(request.getCompleted());
        }

        return this.todoRepository.save(todoDTO);
    }

    // delete by id
    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);
    }

    // delete All
    public void deleteAll() {
        this.todoRepository.deleteAll();
    }
}
