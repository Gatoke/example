package pl.karoldominiak.example.port.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karoldominiak.example.application.model.ProfileReadModel;

@Repository
public interface SpringDataProfileReadModelRepository extends JpaRepository<ProfileReadModel, Long> {

}
