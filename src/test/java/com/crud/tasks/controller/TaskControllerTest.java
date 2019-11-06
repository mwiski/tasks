package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;

    private static final long ID = 1L;
    private static final String TITLE = "TestDto";
    private static final String CONTENT = "Test_ContentDto";
    private Task testeeTask;
    private TaskDto testeeTaskDto;
    private List<TaskDto> testeeTaskDtos;

    @Before
    public void before() {
        testeeTask = new Task(1L, "Test", "Test_Content");
        testeeTaskDto = new TaskDto(ID, TITLE, CONTENT);
        testeeTaskDtos = Arrays.asList(testeeTaskDto);
    }


    @Test
    public void shouldReturnEmptyList() throws Exception {
        //Given
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(new ArrayList<>());

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldReturnAllTasks() throws Exception {
        //Given
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(testeeTaskDtos);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is(TITLE)))
                .andExpect(jsonPath("$[0].content", is(CONTENT)));
    }

    @Test
    public void shouldReturnTask() throws Exception {
        //Given
        when(service.getTask(1L)).thenReturn(Optional.of(testeeTask));
        when(taskMapper.mapToTaskDto(testeeTask)).thenReturn(testeeTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testeeTaskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(TITLE)))
                .andExpect(jsonPath("$.content", is(CONTENT)));
    }

    @Test(expected = TaskNotFoundException.class)
    public void shouldThrowExceptionWhenGetTask() throws Exception {

        //Given & When & Then
        taskMapper.mapToTaskDto(service.getTask(1L).orElseThrow(TaskNotFoundException::new));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        when(service.deleteTask(1L)).thenReturn(true);

        //When & Then
        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("true"));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(testeeTaskDto)))).thenReturn(testeeTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testeeTaskDto);

        //When & Then
        mockMvc.perform(put("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(TITLE)))
                .andExpect(jsonPath("$.content", is(CONTENT)));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(testeeTaskDto)))).thenReturn(testeeTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testeeTaskDto);

        //When & Then
        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.title", is(TITLE)))
                .andExpect(jsonPath("$.content", is(CONTENT)));
    }
}