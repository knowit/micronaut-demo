package no.knowit.jvm.chapter.controller;

import static io.micronaut.http.HttpResponse.created;
import static io.micronaut.http.HttpStatus.NOT_FOUND;
import static io.micronaut.http.HttpStatus.OK;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

import java.util.Optional;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import no.knowit.jvm.chapter.model.Task;
import no.knowit.jvm.chapter.repository.TaskRepository;

@Controller("/api/task")
public class TaskController {

    @Inject
    TaskRepository taskRepository;

    @Get
    public HttpResponse getTasks(@QueryValue("parent") @NotNull long parentId) {

        return taskRepository.findByParentId(parentId)
                .filter(tasks -> !tasks.isEmpty())
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::noContent);
        //return Collections.singletonList(new Task(parentId, "test", TaskStatus.NOT_STARTED));
    }

    @Post
    public HttpResponse createTask(@Body Task task, HttpRequest request) {
        return Optional.of(taskRepository.save(task))
                .map(t -> created(fromUri(request.getUri()).path("/{id}").buildAndExpand(t.getId()).toUri()))
                .orElseGet(HttpResponse::badRequest);
    }

    @Delete
    public HttpStatus deleteTasksByParentId(@QueryValue("parent") @NotNull long parentId) {
        return taskRepository.deleteByParentId(parentId) >= 0 ? OK : NOT_FOUND;
    }

    @Get("/{id}")
    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Put("/{id}")
    public HttpStatus updateTaskById(long id, @Body Task task) {
        return taskRepository.update(id, task) == 1 ? OK : NOT_FOUND;
    }

    @Delete("/{id}")
    public HttpStatus deleteTaskById(long id) {
        return taskRepository.delete(id) == 1 ? OK : NOT_FOUND;
    }

}
