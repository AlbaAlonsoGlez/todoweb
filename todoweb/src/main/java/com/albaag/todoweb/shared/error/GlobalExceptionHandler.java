package com.albaag.todoweb.shared.error;

import com.albaag.todoweb.task.exception.EmptyTaskListException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmptyTaskListException.class)
    public String emptyTaskList(EmptyTaskListException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("emptyListError", true);
        return "redirect:/";
    }
}
