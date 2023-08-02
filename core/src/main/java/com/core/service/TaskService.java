package com.core.service;

import com.core.dto.TaskConclusionDto;
import com.core.dto.TaskCreateDto;
import com.core.dto.TaskUpdateDto;
import com.core.entities.Task;
import com.core.entities.Todo;
import com.core.entities.User;
import com.core.repositories.TaskRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private OauthService oauthService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private LoginService loginService;

    public static final String ADMIN_EMAIL = "admin@todo.com";

    public Task createNewTask(TaskCreateDto taskDto, String token){
        User user = this.oauthService.getUserByToken(token);
        try {
            Todo todo = Todo.builder().id(UUID.fromString(taskDto.getTodoId())).build();
            this.todoService.findOneTodoOfUser(todo.getId(), user);

            return taskRepository.save(Task.builder()
                    .todo(todo)
                    .name(taskDto.getName())
                    .conclusion(taskDto.getConclusion())
                    .createdDate(LocalDateTime.now())
                    .lastUpdatedDate(LocalDateTime.now())
                    .build());
        }catch (ResponseStatusException n){
            log.error(n.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR Not found Todo in DB with id: " + taskDto.getTodoId());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new Task to User: " + user.getId());
        }
    }

    public Page<Task> findAllPageByUserAndTask(String token, UUID todoId, Integer page, Integer linesPerPage){
        User user = this.oauthService.getUserByToken(token);
        try {

            user = loginService.findUserById(user.getId());
            if(user.getEmail().equals(ADMIN_EMAIL)){
                return findAllPageByUserAndTaskAdmin(token, todoId, page, linesPerPage);
            }

            Todo todo = this.todoService.findOneTodoOfUser(todoId, user);

            Pageable paging = PageRequest.of(page, linesPerPage);
            Page<Task> tasks = taskRepository.findAllByTodo(todo, paging);

            tasks.stream().forEach( obj -> {
                obj.setTodo(todo);
            });

            return tasks;
        }catch (ResponseStatusException n){
            log.error(n.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR Find task not found Todo in DB with id: " + todoId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on find pages of Task this Token-User: " + user.getId());
        }
    }

    public Page<Task> findAllPageByUserAndTaskAdmin(String token, UUID todoId, Integer page, Integer linesPerPage){
        User user = this.oauthService.getUserByToken(token);
        try {
            user = loginService.findUserById(user.getId());
            if(!user.getEmail().equals(ADMIN_EMAIL)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ERROR user not is admin Token-User: " + token);
            }

            Todo todo = this.todoService.findTodoByAdmin(todoId);

            Pageable paging = PageRequest.of(page, linesPerPage);
            Page<Task> tasks = taskRepository.findAllByTodo(todo, paging);

            return tasks;
        }catch (ResponseStatusException n){
            log.error(n.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR Find task not found Todo in DB with id: " + todoId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on find pages of Task this Token-User: " + user.getId());
        }
    }

    public Task updateTask(String token, TaskUpdateDto taskDto){
        User user = this.oauthService.getUserByToken(token);
        try {
            Todo todo = todoService.findOneTodoOfUser(UUID.fromString(taskDto.getTodoId()), user);
            Task task = this.findOneTaskOfTodo(UUID.fromString(taskDto.getId()), todo);

            if(task.isStatus()){
                log.warn("attempt to edit task id: {} and user id: {} and token: {}",
                        taskDto.getId(), user.getId(), token);
                throw new ResponseStatusException(HttpStatus.LOCKED,
                        "ERROR Not found Task in DB with id: " + taskDto.getTodoId());
            }

            task.setName(taskDto.getName());
            task.setConclusion(taskDto.getConclusion());
            task.setLastUpdatedDate(LocalDateTime.now());

            return taskRepository.save(task);
        }catch (ResponseStatusException n){
            log.error(n.getMessage());
            if(HttpStatus.LOCKED.value() == n.getStatus().value()){
                throw new ResponseStatusException(HttpStatus.LOCKED,
                        "ERROR Task conclusion true not is possible edit task id: " + taskDto.getTodoId());
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "ERROR Not found Task in DB with id: " + taskDto.getTodoId());
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new Task to User: " + user.getId());
        }
    }

    public Task findOneTaskOfTodo(UUID taskId, Todo todo) throws NotFoundException{
        try {
            return taskRepository.findByIdAndTodo(taskId, todo).orElseThrow(
                    () -> new NotFoundException("Not Found Task")
            );
        }catch (NotFoundException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR Not found Task in DB with id: " + taskId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("ERROR In found Task in DB with id: " + taskId);
        }
    }

    public Task conclusionTaskOfTodo(String token, TaskConclusionDto taskConclusionDto){
        User user = this.oauthService.getUserByToken(token);
        try {
            Todo todo = todoService.findOneTodoOfUser(UUID.fromString(taskConclusionDto.getTodoId()), user);
            Task task = this.findOneTaskOfTodo(UUID.fromString(taskConclusionDto.getId()), todo);

            task.setStatus(!task.isStatus());
            task.setLastUpdatedDate(LocalDateTime.now());

            return taskRepository.save(task);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR Not found Task in DB with id: " + taskConclusionDto.getId());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("ERROR In set conclusion Task in DB with id: " + taskConclusionDto.getId());
        }
    }

    public Task findById(String token, UUID taskId ){
        User user = this.oauthService.getUserByToken(token);
        try {
            Task task = taskRepository.findById(taskId).orElseThrow(
                    () -> new NotFoundException("Not Found Task")
            );

            if(!task.getTodo().getUser().getId().equals(user.getId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ERROR User try get Task of other user in DB with token: " + token);
            }

            return task;
        }catch (NotFoundException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR Not found Task in DB with id: " + taskId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("ERROR In find Task in DB with id: " + taskId);
        }
    }
}
