package com.albaag.todoweb.task.exception;

import jakarta.persistence.EntityNotFoundException;

public class TaskNotFoundException  extends EntityNotFoundException{
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(Long id) {
        super("No se ha encontrado la tarea con Id: %d".formatted(id));
    }
}
