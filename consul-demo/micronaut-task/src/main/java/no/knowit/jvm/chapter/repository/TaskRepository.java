package no.knowit.jvm.chapter.repository;

import java.util.List;

import no.knowit.jvm.chapter.model.Task;

public interface TaskRepository {

    List<Task> getTaskByParentId(long id);

}
