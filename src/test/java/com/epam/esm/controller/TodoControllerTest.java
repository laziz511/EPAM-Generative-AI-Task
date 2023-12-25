package com.epam.esm.controller;

import com.epam.esm.constants.TestConstants;
import com.epam.esm.model.Todo;
import com.epam.esm.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Test
    void createTodo() throws Exception {
        // Mocking service behavior
        Todo todoToCreate = TestConstants.createTodo1();
        Mockito.when(todoService.saveOrUpdateTodo(any(Todo.class))).thenReturn(todoToCreate);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post(TestConstants.API_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestConstants.asJsonString(todoToCreate)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.title").value(TestConstants.TODO_TITLE_1));
    }

    @Test
    void getTodoById() throws Exception {
        // Mocking service behavior
        Long todoId = TestConstants.TODO_ID_1;
        Todo todo = TestConstants.createTodo1();
        Mockito.when(todoService.getTodoById(todoId)).thenReturn(todo);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.API_BASE_PATH + "/{id}", todoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").value(TestConstants.TODO_TITLE_1));
    }

    @Test
    void getAllTodos() throws Exception {
        // Mocking service behavior
        List<Todo> todos = Arrays.asList(TestConstants.createTodo1(), TestConstants.createTodo2());
        Mockito.when(todoService.getAllTodos()).thenReturn(todos);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.API_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].title").value(TestConstants.TODO_TITLE_1))
                .andExpect(jsonPath("$[1].title").value(TestConstants.TODO_TITLE_2));
    }

    @Test
    void updateTodo() throws Exception {
        // Mocking service behavior
        Long todoId = TestConstants.TODO_ID_1;
        Todo existingTodo = TestConstants.createTodo1();
        Todo updatedTodo = new Todo("Updated Task", "Updated Description");
        updatedTodo.setId(todoId);

        Mockito.when(todoService.getTodoById(todoId)).thenReturn(existingTodo);
        Mockito.when(todoService.saveOrUpdateTodo(any(Todo.class))).thenReturn(updatedTodo);

        // Perform the PUT request
        mockMvc.perform(MockMvcRequestBuilders.put(TestConstants.API_BASE_PATH + "/{id}", todoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestConstants.asJsonString(updatedTodo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void deleteTodoById() throws Exception {
        // Mocking service behavior
        Long todoId = TestConstants.TODO_ID_1;

        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete(TestConstants.API_BASE_PATH + "/{id}", todoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
