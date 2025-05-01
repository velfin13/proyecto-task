package me.velfinvelasquez.services.impl;

import java.time.LocalDate;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import me.velfinvelasquez.models.entities.Task;
import me.velfinvelasquez.models.repositories.TaskRepository;
import me.velfinvelasquez.services.TaskService;
import me.velfinvelasquez.util.ResponseUtil;

/**
 * TaskServiceImpl.java
 * 
 * Implementation of the TaskService interface to handle business logic
 * for managing tasks in the system, such as creation, retrieval,
 * update, and deletion.
 * 
 * Author: Velfin Velasquez
 * Email: velfinvelasquez@gmail.com
 * Since: 2025-05-30
 */
@ApplicationScoped
public class TaskServiceImpl implements TaskService {

    @Inject
    TaskRepository taskRepository;

    /**
     * Returns a list of all tasks.
     * 
     * @return Response containing the list of tasks and a success message
     */
    @Override
    public Response listAll() {
        return ResponseUtil.success(taskRepository.listAll(), "Lista de tareas");
    }

    /**
     * Returns a list of tasks filtered by status.
     * If status is null or blank, returns all tasks.
     * 
     * @param status the status to filter tasks by
     * @return Response containing the list of filtered tasks and a success message
     */
    @Override
    public Response findByStatus(String status) {
        List<Task> tasks = status == null || status.isBlank()
                ? taskRepository.listAll()
                : taskRepository.list("status = ?1", status);
        return ResponseUtil.success(tasks, "Tareas encontradas");
    }

    /**
     * Retrieves a task by its ID.
     * 
     * @param id the ID of the task
     * @return Response containing the task if found, or an error message
     */
    @Override
    public Response findById(Long id) {
        return taskRepository.findByIdOptional(id)
                .map(task -> ResponseUtil.success(task, "Tarea encontrada"))
                .orElse(ResponseUtil.error("Tarea no encontrada", Response.Status.NOT_FOUND));
    }

    /**
     * Creates a new task. Validates that due date is not before today.
     * 
     * @param task the task to create
     * @return Response containing the created task or an error message
     */
    @Override
    @Transactional
    public Response create(Task task) {
        LocalDate today = LocalDate.now();
        if (task.dueDate != null && task.dueDate.isBefore(today)) {
            return ResponseUtil.error("La fecha de vencimiento no puede ser anterior a hoy",
                    Response.Status.BAD_REQUEST);
        }

        task.createdAt = today;
        task.persist();
        return ResponseUtil.success(task, "Tarea creada correctamente");
    }

    /**
     * Updates an existing task. Validates that due date is not before created date.
     * 
     * @param id the ID of the task to update
     * @param updated the updated task data
     * @return Response containing the updated task or an error message
     */
    @Override
    @Transactional
    public Response update(Long id, Task updated) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            return ResponseUtil.error("Tarea no encontrada", Response.Status.NOT_FOUND);
        }

        if (updated.dueDate != null && updated.dueDate.isBefore(task.createdAt)) {
            return ResponseUtil.error("La fecha de vencimiento no puede ser anterior a la fecha de creación",
                    Response.Status.BAD_REQUEST);
        }

        task.title = updated.title;
        task.description = updated.description;
        task.status = updated.status;
        task.dueDate = updated.dueDate;
        return ResponseUtil.success(task, "Tarea actualizada correctamente");
    }

    /**
     * Deletes a task by ID.
     * 
     * @param id the ID of the task to delete
     * @return Response indicating success or failure
     */
    @Override
    @Transactional
    public Response delete(Long id) {
        if (taskRepository.findByIdOptional(id).isEmpty()) {
            return ResponseUtil.error("Tarea no encontrada", Response.Status.NOT_FOUND);
        }
        taskRepository.deleteById(id);
        return ResponseUtil.success(null, "Tarea eliminada correctamente");
    }
}
