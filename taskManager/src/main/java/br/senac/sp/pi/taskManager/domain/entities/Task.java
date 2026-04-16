package br.senac.sp.pi.taskManager.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TASKS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Task {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @Column(name= "TITLE", length = 30, nullable = false )
    private @Getter @Setter String title;

    @Column(name= "DESCRIPTION", length = 150, nullable = true )
    private @Getter @Setter String description;

    @Column(name= "CREATION_DATE", nullable = false )
    private @Getter @Setter Date creationDate;

    @Column(name= "COMPLETION_DATE", nullable = true )
    private @Getter @Setter Date completionDate;

    @Column(name= "due_DATE", nullable = true )
    private @Getter @Setter Date dueDate;

    private transient String inutil;

    @Enumerated(EnumType.ORDINAL)
    @Column(name= "PRIORITY", nullable = false )
    private @Getter @Setter TaskPriority priority;

    @Enumerated( EnumType.STRING)
    @Column(name= "STATUS", nullable = false, length = 11)
    private @Getter @Setter TaskStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}