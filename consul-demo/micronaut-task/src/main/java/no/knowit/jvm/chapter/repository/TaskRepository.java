package no.knowit.jvm.chapter.repository;

import java.util.List;
import java.util.Optional;

import no.knowit.jvm.chapter.model.Task;

public interface TaskRepository {

    Optional<List<Task>> findByParentId(long id);

    Optional<Task> findById(long id);

    Task save(Task task);

    int update(long id, Task task);

    int delete(long id);

    int deleteByParentId(long id);

    Optional<List<Task>> findAll();
}
