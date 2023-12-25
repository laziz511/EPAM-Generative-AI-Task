package com.epam.esm.service;

import com.epam.esm.model.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> getAllTodos();

    Todo getTodoById(Long id);

    Todo saveOrUpdateTodo(Todo todo);

    void deleteTodoById(Long id);

}
