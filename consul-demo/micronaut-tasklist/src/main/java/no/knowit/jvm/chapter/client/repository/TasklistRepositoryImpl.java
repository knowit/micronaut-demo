package no.knowit.jvm.chapter.client.repository;

import java.util.List;
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
    @Transactional(readOnly = true)
    public Optional<Tasklist> findById(long id) {
        return Optional.ofNullable(entityManager.find(Tasklist.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Tasklist>> findAll() {
        return Optional.of(entityManager.createQuery("SELECT t from Tasklist t", Tasklist.class).getResultList());
    }

    @Override
    @Transactional
    public Tasklist save(Tasklist tasklist) {
        entityManager.persist(tasklist);
        return tasklist;
    }

    @Override
    @Transactional
    public int update(long id, Tasklist tasklist) {
        return entityManager.createQuery("UPDATE Tasklist SET title = :title, owner = :owner WHERE id = :id")
                .setParameter("title", tasklist.getTitle())
                .setParameter("owner", tasklist.getOwner())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public int delete(long id) {
        return entityManager.createQuery("DELETE FROM Tasklist where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
