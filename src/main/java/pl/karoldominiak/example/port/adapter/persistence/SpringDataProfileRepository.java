package pl.karoldominiak.example.port.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karoldominiak.example.domain.model.Profile;

@Repository
interface SpringDataProfileRepository extends JpaRepository<Profile, Long> {

}
