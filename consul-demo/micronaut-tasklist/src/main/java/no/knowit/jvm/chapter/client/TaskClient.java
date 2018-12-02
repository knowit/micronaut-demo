package no.knowit.jvm.chapter.client;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Maybe;
import no.knowit.jvm.chapter.client.operations.TaskOperations;
import no.knowit.jvm.chapter.model.Task;

@Client(id = "micronaut-task")
public interface TaskClient extends TaskOperations {

    @Get("/api/task")
    Maybe<List<Task>> getTasksByParentId(@QueryValue("parent") @NotNull long id);

    @Delete("/api/task")
    Maybe<HttpStatus> deleteTasksByParentId(@QueryValue("parent") @NotNull long id);
}
