package no.knowit.jvm.chapter.client.operations;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.reactivex.Maybe;
import no.knowit.jvm.chapter.model.Task;

public interface TaskOperations {

    public Maybe<List<Task>> getTasksByParentId(@NotNull long id);

}
