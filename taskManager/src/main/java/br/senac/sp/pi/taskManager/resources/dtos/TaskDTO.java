package br.senac.sp.pi.taskManager.resources.dtos;

import br.senac.sp.pi.taskManager.domain.entities.Task;
import br.senac.sp.pi.taskManager.domain.entities.TaskPriority;
import br.senac.sp.pi.taskManager.domain.entities.TaskStatus;

import java.util.Date;

public record TaskDTO(Long id,
                      String title,
                      String description,
                      Date creationDate,
                      Date completionDate,
                      Date dueDate,
                      TaskPriority priority,
                      TaskStatus status
) {

    public static TaskDTO fromEntity( Task entity) {
        return new TaskDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getCreationDate(),
                entity.getCompletionDate(),
                entity.getDueDate(),
                entity.getPriority(),
                entity.getStatus()
        );
    }
    public static Task fromDTO( TaskDTO dto ){
        return Task.builder()
                .id(dto.id)
                .title(dto.title)
                .description(dto.description)
                .completionDate(dto.completionDate)
                .dueDate(dto.dueDate)
                .creationDate(dto.creationDate)
                .priority(dto.priority)
                .status(dto.status)
                .build();
    }
}