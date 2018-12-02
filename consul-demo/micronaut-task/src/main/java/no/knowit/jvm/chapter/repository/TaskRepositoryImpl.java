package no.knowit.jvm.chapter.repository;

import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import io.micronaut.spring.tx.annotation.Transactional;
import no.knowit.jvm.chapter.model.Task;

@Singleton
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Task>> findByParentId(@NotNull long id) {
        return Optional.of(entityManager.createQuery("SELECT t FROM Task t WHERE t.parent = :id", Task.class)
                .setParameter("id", id)
                .getResultList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Task> findById(long id) {
        return Optional.of(entityManager.find(Task.class, id));
    }

    @Override
    @Transactional
    public Task save(Task task) {
        entityManager.persist(task);
        return task;
    }

    @Override
    @Transactional
    public int update(long id, Task task) {
        return entityManager.createQuery("UPDATE Task SET status = :status, description = :description, parent = :parent WHERE id = :id")
                .setParameter("status", task.getStatus())
                .setParameter("description", task.getDescription())
                .setParameter("parent", task.getParent())
                .executeUpdate();
    }

    @Override
    @Transactional
    public int delete(long id) {
        return entityManager.createQuery("DELETE FROM Task WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public int deleteByParentId(long id) {
        return entityManager.createQuery("DELETE FROM Task WHERE parent = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
