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
    @Transactional
    public Optional<List<Task>> findByParentId(@NotNull long id) {
        return Optional.of(entityManager.createQuery("select t from Task t where t.parent = :id", Task.class)
                .setParameter("id", id)
                .getResultList());
    }

    @Override
    @Transactional
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
        return entityManager.createQuery("UPDATE Task SET status = :status, description = :description, parent = :parent where id = :id")
                .setParameter("status", task.getStatus())
                .setParameter("description", task.getDescription())
                .setParameter("parent", task.getParent())
                .executeUpdate();
    }

    @Override
    @Transactional
    public int delete(long id) {
        return entityManager.createQuery("DELETE from Task where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
