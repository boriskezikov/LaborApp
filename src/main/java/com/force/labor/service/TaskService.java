package com.force.labor.service;


import com.force.labor.domain.Task;
import com.force.labor.dto.FindTasksDTO;
import com.force.labor.dto.TaskDTO;
import com.force.labor.mapper.TaskMapper;
import com.force.labor.repository.TaskRepository;
import com.force.labor.repository.custom.TaskRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;

import static java.time.Clock.systemUTC;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepositoryCustom taskRepositoryCustom;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    public void create(TaskDTO taskDTO) {
        var now = (now(systemUTC()));
        Task newTask = taskMapper.dtoToTask(taskDTO);
        newTask.setCreated(now);
        newTask.setUpdated(now);
        taskRepository.save(newTask);
    }

    public void updateTask(List<TaskDTO> tasks) {
        tasks.forEach(taskDTO -> taskRepository.findById(taskDTO.getId()).ifPresentOrElse(task -> {
            taskMapper.updateCustomerFromDto(taskDTO, task);
            taskRepository.save(task);
        }, () -> {
            throw new EntityNotFoundException("Task not found!");
        }));
    }

    public List<TaskDTO> find(FindTasksDTO findTasksDTO) {
        return taskMapper.tasksToDtos(
                taskRepositoryCustom.find(findTasksDTO.getCriteria(), findTasksDTO.getSort()));
    }

    public List<TaskDTO> findAll() {
        return taskMapper.tasksToDtos(taskRepository.findAll());
    }

    public void deleteById(BigInteger id) {
        taskRepository.deleteById(id);
    }

}
