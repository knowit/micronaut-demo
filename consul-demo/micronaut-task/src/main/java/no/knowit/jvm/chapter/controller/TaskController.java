package no.knowit.jvm.chapter.controller;

import java.util.Collections;
import java.util.List;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import no.knowit.jvm.chapter.model.Task;
import no.knowit.jvm.chapter.model.TaskStatus;

@Controller("/api/task")
public class TaskController {


    @Get
    public List<Task> getTasks(@QueryValue("parent") long parentId) {

        return Collections.singletonList(new Task(parentId, "test", TaskStatus.NOT_STARTED));

    }

}
