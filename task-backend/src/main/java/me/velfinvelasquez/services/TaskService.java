package me.velfinvelasquez.services;

import jakarta.ws.rs.core.Response;
import me.velfinvelasquez.models.entities.Task;

public interface TaskService {
   Response listAll();
    Response findByStatus(String status);
    Response findById(Long id);
    Response create(Task task);
    Response update(Long id, Task task);
    Response delete(Long id);

}