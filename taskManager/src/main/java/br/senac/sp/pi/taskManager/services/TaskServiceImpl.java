package br.senac.sp.pi.taskManager.services;

import br.senac.sp.pi.taskManager.datasource.repositories.TaskRepository;
import br.senac.sp.pi.taskManager.domain.entities.Task;
import br.senac.sp.pi.taskManager.domain.entities.TaskPriority;
import br.senac.sp.pi.taskManager.domain.entities.TaskStatus;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class TaskService {
    private final TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllByOrderByDueDateAsc(TaskPriority priority) {
        return this.taskRepository.findAllByPriorityOrderByDueDateAsc(priority);
    }

    public List<Task> findByStatusOrderByDueDate(TaskStatus taskStatus) {
        return this.taskRepository.findByStatusOrderByDueDateAsc(taskStatus);
    }

    public List<Task> findAll(Sort sort) {
        return this.taskRepository.findAll();
    }

    public List<Task> findAllByExample (Task task) {
        return this.taskRepository.findAll(Example.of(task));
    }

    public Optional<Task> findById (Long id) {
        return this.taskRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return this.taskRepository.existsById(id);
    }

    public Long countEntities() {
        return this.taskRepository.count();
    }

    public void deleteById(Long id) {
        this.taskRepository.deleteById(id);
    }

    public void delete(Task task) {
        this.taskRepository.delete(task);
    }

    public Task save (Task task) {
        return this.taskRepository.save(task);
    }

    public List<Task> save(List <Task> tasks) {
        return this.taskRepository.saveAll(tasks);
    }

    public Task update(Task task) {

    }