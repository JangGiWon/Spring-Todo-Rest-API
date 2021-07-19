package todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import todo.model.RequestDTO;
import todo.model.ResponseDTO;
import todo.model.TodoDTO;
import todo.service.TodoService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {

    private final TodoService service;

    // CREATE
    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody RequestDTO request) {
        if(ObjectUtils.isEmpty(request.getContent())) {
            return ResponseEntity.badRequest().build();
        }
        if(ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }

        TodoDTO dto = this.service.add(request);
        return ResponseEntity.ok(new ResponseDTO(dto));
    }

    // READ
    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> read(@PathVariable Long id) {
        TodoDTO dto = this.service.findById(id);
        return ResponseEntity.ok(new ResponseDTO(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> readAll() {
        List<TodoDTO> dtoList = this.service.findAll();
        List<ResponseDTO> responseDTOList = dtoList.stream().map(ResponseDTO::new)
                                                                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOList);
    }

    // UPDATE
    @PatchMapping("{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id, @RequestBody RequestDTO request) {
        TodoDTO dto = this.service.updateById(id, request);
        return ResponseEntity.ok(new ResponseDTO(dto));
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }

}
