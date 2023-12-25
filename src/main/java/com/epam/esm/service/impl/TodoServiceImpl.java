package com.epam.esm.service.impl;

import com.epam.esm.exception.TodoNotFoundException;
import com.epam.esm.model.Todo;
import com.epam.esm.repository.TodoRepository;
import com.epam.esm.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
    }

    @Override
    @Transactional
    public Todo saveOrUpdateTodo(Todo todo) {
        if (todo.getId() == null) {
            return todoRepository.save(todo);
        } else {
            Todo existingTodo = todoRepository.findById(todo.getId())
                    .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + todo.getId()));
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setDescription(todo.getDescription());

            return todoRepository.save(existingTodo);
        }
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}
