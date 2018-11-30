package no.knowit.jvm.chapter.client.repository;

import java.util.Optional;

import no.knowit.jvm.chapter.model.Tasklist;

public interface TasklistRepository {

    Optional<Tasklist> findById( long id) ;

}
