package com.epam.esm.constants;

import com.epam.esm.model.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class TestConstants {

    public static final Long TODO_ID_1 = 1L;
    public static final Long TODO_ID_2 = 2L;

    public static final String TODO_TITLE_1 = "Task 1";
    public static final String TODO_TITLE_2 = "Task 2";

    public static final String TODO_DESCRIPTION_1 = "Description 1";
    public static final String TODO_DESCRIPTION_2 = "Description 2";

    public static Todo createTodo1() {
        Todo todo = new Todo(TODO_TITLE_1, TODO_DESCRIPTION_1);
        todo.setId(TODO_ID_1);
        return todo;
    }

    public static Todo createTodo2() {
        Todo todo = new Todo(TODO_TITLE_2, TODO_DESCRIPTION_2);
        todo.setId(TODO_ID_2);
        return todo;
    }

    public static List<Todo> createTodoList() {
        return Arrays.asList(createTodo1(), createTodo2());
    }

    public static final String API_BASE_PATH = "/api/todos";

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
