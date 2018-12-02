package no.knowit.jvm.chapter.client.repository;

import java.util.List;
import java.util.Optional;

import no.knowit.jvm.chapter.model.Tasklist;

public interface TasklistRepository {

    Optional<Tasklist> findById( long id) ;

    Optional<List<Tasklist>> findAll();

    Tasklist save(Tasklist tasklist);

    int update(long id, Tasklist tasklist);

    int delete(long id);
}
