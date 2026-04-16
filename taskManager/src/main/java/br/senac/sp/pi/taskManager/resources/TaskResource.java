package br.senac.sp.pi.taskManager.resources;

import br.senac.sp.pi.taskManager.domain.entities.Task;
import br.senac.sp.pi.taskManager.resources.dtos.TaskDTO;
import br.senac.sp.pi.taskManager.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//http://localhost:8080/api/tasks
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskService taskService;

    //http://localhost:8080/api/tasks/1
    //buscar por id (read)
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

    //criar (create)
    @PostMapping
    public ResponseEntity<TaskDTO> save( @RequestBody TaskDTO taskDTO ){
        Task task = this.taskService.save(TaskDTO.fromDTO(taskDTO));

        return ResponseEntity.ok(TaskDTO.fromEntity(task));
    }

    //listar tarefas (read all)
    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll(){
        List<Task> tasks = this.taskService.findAll();

        List<TaskDTO> dtoList = tasks.stream()
                .map(TaskDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id,
                                          @RequestBody TaskDTO taskDTO){

        if(!taskService.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        Task task = TaskDTO.fromDTO(taskDTO);
        task.setId(id); //garante que vai atualizar

        Task updated = taskService.update(task);

        return ResponseEntity.ok(TaskDTO.fromEntity(updated));
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        if(!taskService.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        taskService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}