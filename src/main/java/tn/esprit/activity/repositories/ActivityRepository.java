package tn.esprit.activity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.activity.entities.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}

