package at.ta.todos.api.todosApi.repo;

import at.ta.todos.api.todosApi.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TodoRepository extends JpaRepository <Todo, Integer> {

    @Transactional
    @Modifying
    @Query("update Todo t set t.isDone = :isDone where t.id = :id")
    void updateTodo(@Param(value = "id") int id, @Param(value = "isDone") boolean isDone);

}
