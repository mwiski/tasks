package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    private final Task testee = new Task(1L, "Test_task", "Test");
    private final TaskDto testeeDto = new TaskDto(2L, "Test_taskDto", "Test_Dto");
    private final List<Task> testeeList = Arrays.asList(testee);

    @Test
    public void mapToTaskWhenDtoIsEmpty() {
        //Given & When
        Task task = taskMapper.mapToTask(new TaskDto());

        //Then
        assertThat(task).isEqualToComparingFieldByField(new Task());
    }

    @Test
    public void mapToTaskWhenDtoIsNull() {
        //Given & When
        Task task = taskMapper.mapToTask(null);

        //Then
        assertThat(task).isNull();
    }

    @Test
    public void mapToTask() {
        //Given & When
        Task task = taskMapper.mapToTask(testeeDto);

        //Then
        assertThat(task).isEqualToComparingFieldByField(new Task(2L, "Test_taskDto", "Test_Dto"));
    }

    @Test
    public void mapToTaskDtoWhenTaskIsEmpty() {
        //Given & When
        TaskDto taskDto = taskMapper.mapToTaskDto(new Task());

        //Then
        assertThat(taskDto).isEqualToComparingFieldByField(new TaskDto());
    }

    @Test
    public void mapToTaskDtoWhenTaskIsNull() {
        //Given & When
        TaskDto taskDto = taskMapper.mapToTaskDto(null);

        //Then
        assertThat(taskDto).isNull();
    }

    @Test
    public void mapToTaskDto() {
        //Given & When
        TaskDto taskDto = taskMapper.mapToTaskDto(testee);

        //Then
        assertThat(taskDto).isEqualToComparingFieldByField(new TaskDto(1L, "Test_task", "Test"));
    }

    @Test
    public void mapToTaskDtoListWhenListIsEmpty() {
        //Given & When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(new ArrayList<>());

        //Then
        assertThat(taskDtoList).isEmpty();
    }

    @Test
    public void mapToTaskDtoListWhenListIsNull() {
        //Given & When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(null);

        //Then
        assertThat(taskDtoList).isNull();
    }

    @Test
    public void mapToTaskDtoList() {
        //Given & When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(testeeList);

        //Then
        assertThat(taskDtoList.size()).isEqualTo(1);
        assertThat(taskDtoList.get(0)).isEqualToComparingFieldByField(new TaskDto(1L, "Test_task", "Test"));
    }
}