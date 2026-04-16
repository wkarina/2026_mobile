package br.senac.sp.pi.taskManager.datasource.repositories;

import br.senac.sp.pi.taskManager.domain.entities.Task;
import br.senac.sp.pi.taskManager.domain.entities.TaskPriority;
import br.senac.sp.pi.taskManager.domain.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByPriorityOrderByDueDateAsc(TaskPriority priority);
    public List<Task> findByStatusOrderByDueDateAsc(TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.title = :title")
    List<Task> findByTitleJPQL(@Param("title") String title);

    @Query("""
            SELECT t FROM Task t
                WHERE t.status = :status
                    AND t.priority = :priority
                        AND t.priority = :priority
                        AND t.creationDate >= :startDate
                        AND t.dueDate <= :endDate
            """)
    List<Task> findAdvancedJPQL(
            @Param("status") TaskStatus status,
            @Param("status") TaskPriority priority,
            @Param("status") Date startDate,
            @Param("status") Date endDate

    );
}