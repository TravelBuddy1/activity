package tn.esprit.activity.services;

import tn.esprit.activity.entities.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    List<Activity> findAll();
    Optional<Activity> findById(Long id);
    Activity create(Activity activity);
    Activity update(Long id, Activity activity);
    void delete(Long id);
}

