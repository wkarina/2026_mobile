package br.senac.sp.pi.taskManager.services;

import br.senac.sp.pi.taskManager.datasource.repositories.TaskRepository;
import br.senac.sp.pi.taskManager.domain.entities.Task;
import br.senac.sp.pi.taskManager.domain.entities.TaskPriority;
import br.senac.sp.pi.taskManager.domain.entities.TaskStatus;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Task> findAllByOrderByDueDateAsc(TaskPriority priority){
        return this.taskRepository.findAllByPriorityOrderByDueDateAsc( priority);
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Task> findByStatusOrderByDueDate(TaskStatus taskStatus){
        return this.taskRepository.findByStatusOrderByDueDateAsc(taskStatus);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Page<Task> findAll(Pageable pageable){
        return this.taskRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Task> findAll(Sort sort){
        return this.taskRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Task> findAllByExample(Task task){
        return this.taskRepository.findAll(Example.of(task));
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Optional<Task> findById(Long id){
        return this.taskRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean existsById(long id){
        return this.taskRepository.existsById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long countEntities(){
        return this.taskRepository.count();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id){
        this.taskRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Task task){
        this.taskRepository.delete(task);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Task save(Task task){
        return this.taskRepository.save(task);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Task> save(List<Task> tasks){
        return this.taskRepository.saveAll(tasks);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Task update(Task task){
        return this.taskRepository.save(task);
    }
}