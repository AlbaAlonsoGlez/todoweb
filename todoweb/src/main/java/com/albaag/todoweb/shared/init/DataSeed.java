package com.albaag.todoweb.shared.init;


import com.albaag.todoweb.category.model.Category;
import com.albaag.todoweb.category.model.CategoryRepository;
import com.albaag.todoweb.task.dto.CreateTaskRequest;
import com.albaag.todoweb.task.service.TaskService;
import com.albaag.todoweb.user.dto.CreateUserRequest;
import com.albaag.todoweb.user.model.User;
import com.albaag.todoweb.user.model.UserRole;
import com.albaag.todoweb.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final CategoryRepository categoryRepository;
    private final TaskService taskService;
    private final UserService userService;

    @PostConstruct
    public void init() {
        insertCategories();
        List<User> users =insertUsers();
        insertTasks(users.get(0));
    }

    /*
        Solamente devuelve aquellos que son UserRole.USER
        para poder usarlos como autores de Task
     */
    private List<User> insertUsers() {

        List<User> result = new ArrayList<>();

        CreateUserRequest req = CreateUserRequest.builder()
                .username("Usuario")
                .email("user@educastur.com")
                .password("1234")
                .verifyPassword("1234")
                .fullname("El usuario")
                .build();
        User user = userService.registerUser(req);
        result.add(user);

        CreateUserRequest req2 = CreateUserRequest.builder()
                .username("admin")
                .email("admin@educastur.net")
                .password("1234")
                .verifyPassword("1234")
                .fullname("Administrador")
                .build();
        User user2 = userService.registerUser(req2);

        userService.changeRole(user2, UserRole.ADMIN);

        return result;
    }

    private void insertCategories() {
        categoryRepository.save(Category.builder().title("Main").build());
    }

    private void insertTasks(User author) {

        CreateTaskRequest req1 = CreateTaskRequest.builder()
                .title("Primera tarea :)")
                .description("Ordenar habitación")
                .tags("tag1,tag2,tag3")
                .build();

        taskService.createTask(req1, author);

        CreateTaskRequest req2 = CreateTaskRequest.builder()
                .title("Segunda tarea :)")
                .description("Sacar al perro")
                .tags("tag1,tag2,tag4")
                .build();

        taskService.createTask(req2, author);

    }

}
