package at.ta.todos.api.todosApi.controller;

import at.ta.todos.api.todosApi.model.Todo;
import at.ta.todos.api.todosApi.repo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class TodoController {

    @Autowired
    private TodoRepository repo;

    @GetMapping("/todos")
    List<Todo> getAllTodos() {
        List<Todo> todos = repo.findAll();
        return todos;
    }

    @PostMapping(value = "todos", consumes = "application/json")
    public ResponseEntity insertTodo(@RequestBody Todo todo) {
        repo.save(todo);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/todos/{id}")
    void deleteTodo(@PathVariable Integer id) {
        repo.deleteById(id);
    }

    @PutMapping(value = "/todos/{id}")
    public ResponseEntity change(@PathVariable Integer id, @RequestBody Todo todo) {
        repo.updateTodo(id,todo.getDone());
        return new ResponseEntity(HttpStatus.OK);
    }
}
