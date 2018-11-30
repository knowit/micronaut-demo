package no.knowit.jvm.chapter.client.repository;

import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.micronaut.spring.tx.annotation.Transactional;
import no.knowit.jvm.chapter.model.Tasklist;

@Singleton
public class TasklistRepositoryImpl implements TasklistRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Tasklist> findById(long id) {
        return Optional.ofNullable(entityManager.find(Tasklist.class, id));
    }
}
