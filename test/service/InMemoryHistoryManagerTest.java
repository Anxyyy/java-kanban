package service;

import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private InMemoryHistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void testTaskHistoryPreservation() {
        Task originalTask = new Task("Тестовая задача", Status.NEW, "Проверить тестовую задачу");

        historyManager.addAll(originalTask);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());

        Task retrievedTask = history.get(0);
        assertEquals(originalTask.getId(), retrievedTask.getId());
        assertEquals(originalTask.getName(), retrievedTask.getName());
        assertEquals(originalTask.getStatus(), retrievedTask.getStatus());
        assertEquals(originalTask.getDescription(), retrievedTask.getDescription());
    }

    @Test
    void testDuplicateTaskRemoval() {
        Task task1 = new Task("Task 1", Status.NEW, "Description 1");
        Task task2 = new Task("Task 2", Status.IN_PROGRESS, "Description 2");

        historyManager.addAll(task1);
        historyManager.addAll(task1);
        historyManager.addAll(task2);
        historyManager.addAll(task1);

        List<Task> history = historyManager.getHistory();
        assertEquals(3, history.size());
        assertEquals(task1.getId(), history.get(0).getId());
        assertEquals(task2.getId(), history.get(1).getId());
        assertEquals(task1.getId(), history.get(2).getId());
    }

    @Test
    void testGetHistory() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task " + i, Status.NEW, "Description " + i);
            tasks.add(task);
            historyManager.addAll(task);
        }

        List<Task> history = historyManager.getHistory();
        assertEquals(tasks.size(), history.size());
        for (int i = 0; i < tasks.size(); i++) {
            assertEquals(tasks.get(i).getId(), history.get(i).getId());
        }
    }

    @Test
    void testRemoveTask() {
        Task task1 = new Task("Task 1", Status.NEW, "Description 1");
        Task task2 = new Task("Task 2", Status.IN_PROGRESS, "Description 2");
        Task task3 = new Task("Task 3", Status.DONE, "Description 3");

        historyManager.addAll(task1);
        historyManager.addAll(task2);
        historyManager.addAll(task3);

        historyManager.remove(task2.getId());

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertEquals(task1.getId(), history.get(0).getId());
        assertEquals(task3.getId(), history.get(1).getId());
    }
}