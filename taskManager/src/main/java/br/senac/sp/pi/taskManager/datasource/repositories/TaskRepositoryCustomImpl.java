package br.senac.sp.pi.taskManager.datasource.repositories;

import br.senac.sp.pi.taskManager.domain.entities.Task;
import br.senac.sp.pi.taskManager.domain.entities.TaskPriority;
import br.senac.sp.pi.taskManager.domain.entities.TaskStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TaskRepositoryCustomImpl implements TaskRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    public List<Task> findByTitleCriteria(String title){

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);


        query.select(root)
                .where(
                        builder.equal(root.get("title"), title)
                );

        return this.entityManager.createQuery(query).getResultList();
    }

    public List<Task> findAdvancedCriteria(TaskStatus status, TaskPriority priority,
                                           Date startDate, Date endDate) {

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        Root<Task> root = cq.from(Task.class);

        Predicate p1 = cb.equal(root.get("status"), status);
        Predicate p2 = cb.equal(root.get("priority"), priority);
        Predicate p3 = cb.greaterThanOrEqualTo(root.get("creationDate"), startDate);
        Predicate p4 = cb.lessThanOrEqualTo(root.get("dueDate"), endDate);

        cq.select(root)
                .where(cb.and(p1, p2, p3, p4));

        return entityManager.createQuery(cq).getResultList();
    }
}
