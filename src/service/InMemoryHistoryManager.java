package service;

import model.Task;
import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private static class Node {
        int id;
        Task task;
        Node prev;
        Node next;

        Node(int id, Task task) {
            this.id = id;
            this.task = task;
        }
    }

    private Node head;
    private Node tail;
    private final HashMap<Integer, Node> taskToNode = new HashMap<>();

    @Override
    public void addAll(Task task) {
        int id = task.getId();
        if (taskToNode.containsKey(id)) {
            removeNode(taskToNode.get(id));
        }
        Node newNode = new Node(id, task);
        linkLast(newNode);
        taskToNode.put(id, newNode);
    }

    private void linkLast(Node node) {
        Node oldTail = tail;
        tail = node;
        if (oldTail == null) {
            head = node;
        } else {
            oldTail.next = node;
            node.prev = oldTail;
        }
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }
        taskToNode.remove(node.id);
    }

    @Override
    public void remove(int id) {
        Node node = taskToNode.get(id);
        if (node != null) {
            removeNode(node);
        }
    }

    @Override
    public List<Task> getHistory() {
        List<Task> history = new ArrayList<>();
        for (Node node = head; node != null; node = node.next) {
            history.add(node.task);
        }
        return history;
    }
}

