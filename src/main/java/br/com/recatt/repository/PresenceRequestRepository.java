package br.com.recatt.repository;

import br.com.recatt.entity.PresenceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceRequestRepository extends JpaRepository<PresenceRequest, Integer> {

}
