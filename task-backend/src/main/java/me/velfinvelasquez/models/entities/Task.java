package me.velfinvelasquez.models.entities;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Task extends PanacheEntity {
    @NotBlank
    public String title;
    public String description;
    public String status;
    public LocalDate createdAt;
    public LocalDate dueDate;
}
