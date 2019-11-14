package br.com.recatt.repository;

import br.com.recatt.entity.PresenceRequest;
import br.com.recatt.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRequestRepository extends JpaRepository<PresenceRequest, Integer> {

    @Query("FROM PresenceRequest pr " +
            "WHERE pr.presence.actualClass.diary.teacher = :teacher " +
            "AND pr.status = 'AWAITING_REVIEW'")
    List<PresenceRequest> findAllRequests(@Param("teacher") Teacher teacher);

}
