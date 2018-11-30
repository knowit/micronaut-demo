package no.knowit.jvm.chapter.client.controller;

import java.util.List;

import javax.inject.Inject;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import no.knowit.jvm.chapter.client.operations.TaskOperations;
import no.knowit.jvm.chapter.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api/tasklist")
public class TasklistController {

    private final Logger logger = LoggerFactory.getLogger(TasklistController.class);

    @Inject
    private TaskOperations taskOperations;

    @Get("/{id}")
    public List<Task> getTaskListById(long id) {
        logger.info("Entered GetTaskListById");
        return taskOperations.getTasksByParentId(id).blockingGet();
    }

}
