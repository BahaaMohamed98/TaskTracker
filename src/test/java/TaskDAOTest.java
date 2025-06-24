import database.DBInit;
import database.connection.ConnectionProvider;
import database.connection.TestConnectionProvider;
import database.dao.TaskDAO;
import database.mapper.TaskMapper;
import database.model.Task;
import exception.TaskNotFoundException;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task DAO Integration Tests")
@Tag("integration")
class TaskDAOTest {
    private static ConnectionProvider connectionProvider;
    private static TaskDAO taskDAO;

    @BeforeAll
    static void setUpDatabase() throws SQLException {
        connectionProvider = new TestConnectionProvider();
        taskDAO = new TaskDAO(connectionProvider, new TaskMapper());
        new DBInit(connectionProvider).init();
    }

    @BeforeEach
    void setUp() throws SQLException {
        cleanDatabase(); // Clean data before each test
    }

    private void cleanDatabase() throws SQLException {
        try (var connection = connectionProvider.getConnection();
             var stmt = connection.createStatement()
        ) {
            //noinspection SqlWithoutWhere
            stmt.executeUpdate("DELETE FROM TASKS");
        }
    }

    @Test
    @DisplayName("save: Should return task with ID when saving valid task")
    void save_valid_task_should_return_task_with_id() {
        // Given
        Task task = new Task("Learn Java", "Study Java fundamentals");

        // When
        Task savedTask = taskDAO.save(task);

        // Then
        assertNotNull(savedTask);
        assertTrue(savedTask.getId() > 0);
        assertEquals("Learn Java", savedTask.getTitle());
        assertEquals("Study Java fundamentals", savedTask.getDescription());
        assertFalse(savedTask.isDone());
        assertNotNull(savedTask.getCreatedAt());
    }

    @Test
    @DisplayName("findAll: Should return empty list for empty database")
    void find_all_empty_database_should_return_empty_list() {
        // When
        List<Task> tasks = taskDAO.findAll();

        // Then
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("findAll: Should return all tasks when multiple exist")
    void find_all_with_multiple_tasks_should_return_all_tasks() {
        // Given
        taskDAO.save(new Task("Task 1", "Description 1"));
        taskDAO.save(new Task("Task 2", "Description 2"));
        taskDAO.save(new Task("Task 3"));

        // When
        List<Task> tasks = taskDAO.findAll();

        // Then
        assertEquals(3, tasks.size());
    }

    @Test
    @DisplayName("update: Should update existing task successfully")
    void update_existing_task_should_update_successfully() {
        // Given
        Task originalTask = taskDAO.save(new Task("Original", "Original description"));
        originalTask.setTitle("Updated Title");
        originalTask.setDescription("Updated description");
        originalTask.setDone(true);

        // When
        assertDoesNotThrow(() -> taskDAO.update(originalTask));

        // Then
        List<Task> tasks = taskDAO.findAll();
        assertEquals(1, tasks.size());
        Task updatedTask = tasks.getFirst();
        assertEquals("Updated Title", updatedTask.getTitle());
        assertTrue(updatedTask.isDone());
    }

    @Test
    @DisplayName("update: Should throw TaskNotFoundException for non-existent task")
    void update_non_existent_task_should_throw_task_not_found_exception() {
        // Given
        Task nonExistentTask = new Task(999, "Non-existent", "Description", false, LocalDateTime.now());

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> taskDAO.update(nonExistentTask));
    }

    @Test
    @DisplayName("delete: Should delete existing task successfully")
    void delete_existing_task_should_delete_successfully() {
        // Given
        Task task1 = taskDAO.save(new Task("Task 1"));
        Task task2 = taskDAO.save(new Task("Task 2"));
        assertEquals(2, taskDAO.findAll().size(), "Should have 2 tasks before deletion");

        // When
        assertDoesNotThrow(() -> taskDAO.deleteById(task1.getId()));

        // Then
        List<Task> remainingTasks = taskDAO.findAll();
        assertEquals(1, remainingTasks.size(), "Should have 1 task after deletion");
        assertEquals(task2.getId(), remainingTasks.getFirst().getId(), "Remaining task should be task2");
        assertEquals("Task 2", remainingTasks.getFirst().getTitle(), "Remaining task should have title 'Task 2'");
    }

    @Test
    @DisplayName("delete: Should throw TaskNotFoundException for non-existent task")
    void delete_non_existent_task_should_throw_task_not_found_exception() {
        // Given
        Task nonExistentTask = new Task(999, "Non-existent", "Description", false, LocalDateTime.now());

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> taskDAO.deleteById(nonExistentTask.getId()));
    }

    @Test
    @DisplayName("deleteAll: Should remove all tasks from database")
    void delete_all_with_multiple_tasks_should_delete_all_tasks() {
        // Given
        taskDAO.save(new Task("Task 1"));
        taskDAO.save(new Task("Task 2"));

        // When
        assertDoesNotThrow(() -> taskDAO.deleteAll());

        // Then
        assertTrue(taskDAO.findAll().isEmpty());
    }
}