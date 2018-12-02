package no.knowit.jvm.chapter.controller;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import no.knowit.jvm.chapter.model.Task;
import no.knowit.jvm.chapter.model.TaskStatus;
import no.knowit.jvm.chapter.repository.TaskRepository;

@Controller("/api/task")
public class TaskController {

    @Inject
    TaskRepository taskRepository;

    @Get
    public List<Task> getTasks(@QueryValue("parent") long parentId) {

        return taskRepository.getTaskByParentId(parentId);
        //return Collections.singletonList(new Task(parentId, "test", TaskStatus.NOT_STARTED));

    }

}
