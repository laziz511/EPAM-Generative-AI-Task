package com.epam.esm.service;

import com.epam.esm.model.Todo;
import com.epam.esm.repository.TodoRepository;
import com.epam.esm.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.epam.esm.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @Test
    void getAllTodos() {
        // Mocking repository behavior
        Mockito.when(todoRepository.findAll()).thenReturn(createTodoList());

        // Test the service method
        List<Todo> result = todoService.getAllTodos();

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(TODO_TITLE_1, result.get(0).getTitle());
        assertEquals(TODO_TITLE_2, result.get(1).getTitle());
    }

    @Test
    void getTodoById() {
        // Mocking repository behavior
        Long todoId = TODO_ID_1;
        Mockito.when(todoRepository.findById(todoId)).thenReturn(Optional.of(createTodo1()));

        // Test the service method
        Todo result = todoService.getTodoById(todoId);

        // Assertions
        assertNotNull(result);
        assertEquals(todoId, result.getId());
        assertEquals(TODO_TITLE_1, result.getTitle());
    }

    @Test
    void createTodo() {
        // Mocking repository behavior
        Todo todoToCreate = createTodo1();

        // Ensure the ID is null to simulate a new Todo
        todoToCreate.setId(null);

        Mockito.when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(todoToCreate);

        // Test the service method
        Todo result = todoService.saveOrUpdateTodo(todoToCreate);

        // Assertions
        assertNotNull(result);
        assertEquals(TODO_TITLE_1, result.getTitle());
    }



    @Test
    void updateTodo() {
        // Mocking repository behavior
        Long todoId = TODO_ID_1;
        Todo existingTodo = createTodo1();
        Todo updatedTodo = new Todo("Updated Task", "Updated Description");
        updatedTodo.setId(todoId); // Set the id to match the existingTodo

        Mockito.when(todoRepository.findById(todoId)).thenReturn(Optional.of(existingTodo));
        Mockito.when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(updatedTodo);

        // Test the service method
        Todo result = todoService.saveOrUpdateTodo(updatedTodo);

        // Assertions
        assertNotNull(result);
        assertEquals(todoId, result.getId());
        assertEquals(updatedTodo.getTitle(), result.getTitle());
    }

    @Test
    void deleteTodoById() {
        // Mocking repository behavior
        Long todoId = TODO_ID_1;
        Todo todoToDelete = createTodo1();

        Mockito.when(todoRepository.findById(todoId)).thenReturn(Optional.of(todoToDelete));

        // Test the service method
        todoService.deleteTodoById(todoId);

        // Verify that the deleteById method was called
        Mockito.verify(todoRepository, Mockito.times(1)).deleteById(todoId);
    }
}