package edu.pliniopereira10.clientcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pliniopereira10.clientcontrol.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {

}
