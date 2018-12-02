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
    public List<Task> getTaskByParentId(@NotNull long id) {
        return entityManager.createQuery("select t from Task t where t.id = :id", Task.class)
                .setParameter("id", id)
                .getResultList();
    }
}
