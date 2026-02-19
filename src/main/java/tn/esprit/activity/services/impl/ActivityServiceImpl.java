package tn.esprit.activity.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.activity.entities.Activity;
import tn.esprit.activity.repositories.ActivityRepository;
import tn.esprit.activity.services.ActivityService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

    @Override
    public Activity create(Activity activity) {
        activity.setId(null); // ensure it's a create
        return activityRepository.save(activity);
    }

    @Override
    public Activity update(Long id, Activity activity) {
        Activity existing = activityRepository.findById(id).orElseThrow(() -> new java.util.NoSuchElementException("Activity not found with id: " + id));
        existing.setName(activity.getName());
        existing.setDescription(activity.getDescription());
        // don't update createdAt
        return activityRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Activity existing = activityRepository.findById(id).orElseThrow(() -> new java.util.NoSuchElementException("Activity not found with id: " + id));
        activityRepository.delete(existing);
    }
}
