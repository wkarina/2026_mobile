package br.senac.sp.pi.taskManager.services;

import br.senac.sp.pi.taskManager.domain.entities.Task;
import br.senac.sp.pi.taskManager.domain.entities.TaskPriority;
import br.senac.sp.pi.taskManager.domain.entities.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAllByOrderByDueDateAsc(TaskPriority priority);

    List<Task> findByStatusOrderByDueDate(TaskStatus taskStatus);

    List<Task> findAll();

    Page<Task> findAll(Pageable pageable);

    List<Task> findAll(Sort sort);

    List<Task> findAllByExample(Task task);

    Optional<Task> findById(Long id);

    boolean existsById(long id);

    Long countEntities();

    void deleteById(Long id);

    void delete(Task task);

    Task save(Task task);

    List<Task> save(List<Task> tasks);

    Task update(Task task);
}
