package todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private Long id;
    private String content;
    private Boolean completed;

    public ResponseDTO(TodoDTO todoDTO) {
        this.id = todoDTO.getId();
        this.content = todoDTO.getContent();
        this.completed = todoDTO.getCompleted();
    }
}
