package me.velfinvelasquez.models.repositories;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import me.velfinvelasquez.models.entities.Task;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {
    
}
