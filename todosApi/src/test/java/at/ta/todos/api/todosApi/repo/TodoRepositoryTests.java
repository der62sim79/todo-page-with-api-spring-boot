package at.ta.todos.api.todosApi.repo;

import static org.assertj.core.api.Assertions.assertThat;

import at.ta.todos.api.todosApi.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class TodoRepositoryTests {

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void myFirstTest() {
        assertThat(true).isTrue();
    }

    @Test
    public void repositoryNotNullTest() {
       assertThat(todoRepository).isNotNull();
    }

    @Test
    public void addTodoTest() {
        Todo todo = new Todo();
        todo.setName("Testname");
        todoRepository.save(todo);

        Todo todo2 = todoRepository.getOne(todo.getId());
        assertThat(todo2).isEqualTo(todo);
    }

}
