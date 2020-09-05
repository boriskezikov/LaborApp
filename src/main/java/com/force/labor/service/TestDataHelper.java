package com.force.labor.service;

import com.force.labor.domain.Employee;
import com.force.labor.domain.Task;
import com.force.labor.domain.TaskStatus;
import com.force.labor.repository.EmployeeRepository;
import com.force.labor.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TestDataHelper {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    public List<Task> generateEmployees(int eLimit, int tLimit) {
        var empls = Stream.generate(this::getRandomEmployee).limit(eLimit)
                .peek(employeeRepository::save)
                .collect(toList());
        var tasks = generateRandomTaskNumber(tLimit);
        tasks.forEach(task -> task.setEmployee(
                empls.stream()
                        .skip(TestHelper.getRandomInt(0, empls.size()))
                        .collect(Collectors.toUnmodifiableList())));
        return taskRepository.saveAll(tasks);
    }

    private List<Task> generateRandomTaskNumber(int limit) {
        return Stream.generate(this::getRandomTask)
                .limit(TestHelper.getRandomInt(1, limit))
                .collect(toList());

    }

    private Employee getRandomEmployee() {
        return Employee.builder()
                .firstName(TestHelper.getRandomLetters(10))
                .lastName(TestHelper.getRandomLetters(12))
                .passport(TestHelper.getRandomEightDigits())
                .grade(TestHelper.getRandomGrade())
                .build();
    }

    private Task getRandomTask() {
        var now = LocalDateTime.now(Clock.systemUTC());
        return Task.builder()
                .created(now)
                .description(TestHelper.getRandomLetters(60))
                .difficultyLevel(TestHelper.getRandomInt(1, 3))
                .title(TestHelper.getRandomLetters(10))
                .status(TaskStatus.OPEN)
                .estimatedTimeInHours(TestHelper.getRandomInt(1,48))
                .updated(now)
                .priority(TestHelper.getRandomPriority())
                .build();
    }
}
