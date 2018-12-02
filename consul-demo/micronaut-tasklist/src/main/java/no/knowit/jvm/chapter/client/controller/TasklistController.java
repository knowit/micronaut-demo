package no.knowit.jvm.chapter.client.controller;

import static io.micronaut.http.HttpResponse.created;
import static io.micronaut.http.HttpStatus.NOT_FOUND;
import static io.micronaut.http.HttpStatus.OK;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

import java.util.Optional;

import javax.inject.Inject;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import no.knowit.jvm.chapter.client.operations.TaskOperations;
import no.knowit.jvm.chapter.client.repository.TasklistRepository;
import no.knowit.jvm.chapter.model.Tasklist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api/tasklist")
public class TasklistController {

    private final Logger logger = LoggerFactory.getLogger(TasklistController.class);

    @Inject
    private TaskOperations taskOperations;

    @Inject
    private TasklistRepository tasklistRepository;

    @Get
    public HttpResponse getAllTaskLists() {
        return tasklistRepository.findAll()
                .filter(tasklists -> !tasklists.isEmpty())
                .map(tasklists -> tasklists.stream()
                        .peek(tasklist -> tasklist.setTasks(taskOperations.getTasksByParentId(tasklist.getId()).blockingGet(emptyList())))
                        .collect(toList()))
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::noContent);
    }

    @Post
    public HttpResponse createTaskList(@Body Tasklist tasklist, HttpRequest request) {
        return Optional.of(tasklistRepository.save(tasklist))
                .map(tasklist1 -> created(fromUri(request.getUri()).path("/{id}").buildAndExpand(tasklist1.getId()).toUri()))
                .orElseGet(HttpResponse::badRequest);
    }

    @Get("/{id}")
    public HttpResponse getTaskListById(long id) {
        return tasklistRepository.findById(id)
                .map(tasklist -> {
                    tasklist.setTasks(taskOperations.getTasksByParentId(tasklist.getId()).blockingGet(emptyList()));
                    return HttpResponse.ok(tasklist);
                })
                .orElseGet(HttpResponse::notFound);
    }

    @Put("/{id}")
    public HttpStatus updateTasklist(long id, @Body Tasklist tasklist) {
        return tasklistRepository.update(id, tasklist) == 1 ? OK : NOT_FOUND;
    }

    @Delete("/{id}")
    public HttpStatus deleteTasklist(long id) {
        return deleteTasklistAndTasks(id);
    }

    private HttpStatus deleteTasklistAndTasks(long id) {
        HttpStatus taskDeleteStatus = taskOperations.deleteTasksByParentId(id).blockingGet();
        if (taskDeleteStatus != OK) {
            return NOT_FOUND;
        }

        if (tasklistRepository.delete(id) != 1) {
            return NOT_FOUND;
        }return OK;
    }

}
