package br.senac.sp.pi.taskManager.resources;

import br.senac.sp.pi.taskManager.domain.entities.Task;
import br.senac.sp.pi.taskManager.resources.dtos.TaskDTO;
import br.senac.sp.pi.taskManager.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//http://localhost:8080/api/tasks
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskService taskService;

    //http://localhost:8080/api/tasks/1
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> fetchById(@PathVariable Long id){
        Optional<Task> taskOp =
                this.taskService.findById(id);
        return taskOp.map(
                task ->
                        ResponseEntity.ok(
                                TaskDTO.fromEntity(task)
                        )
        ).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public ResponseEntity<TaskDTO> save( @RequestBody TaskDTO taskDTO ){
        Task task = this.taskService.save(TaskDTO.fromDTO(taskDTO));

        return ResponseEntity.ok(TaskDTO.fromEntity(task));
    }
}