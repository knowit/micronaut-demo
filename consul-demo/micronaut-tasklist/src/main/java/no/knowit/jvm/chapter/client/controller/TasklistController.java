package no.knowit.jvm.chapter.client.controller;

import java.util.List;

import javax.inject.Inject;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import no.knowit.jvm.chapter.client.operations.TaskOperations;
import no.knowit.jvm.chapter.client.repository.TasklistRepository;
import no.knowit.jvm.chapter.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api/tasklist")
public class TasklistController {

    private final Logger logger = LoggerFactory.getLogger(TasklistController.class);

    @Inject
    private TaskOperations taskOperations;

    @Inject
    private TasklistRepository tasklistRepository;

    @Get("/{id}")
    public HttpResponse getTaskListById(long id) {

        return tasklistRepository.findById(id)
                .map(tasklist -> {
                    tasklist.setTasks(taskOperations.getTasksByParentId(tasklist.getId()).blockingGet());
                    return HttpResponse.ok(tasklist);
                })
                .orElse(HttpResponse.notFound());
    }

}
