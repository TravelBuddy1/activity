package tn.esprit.activity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.activity.entities.Activity;
import tn.esprit.activity.services.ActivityService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAll() {
        List<Activity> list = activityService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getById(@PathVariable Long id) {
        Activity activity = activityService.findById(id).orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Activity not found with id: " + id));
        return ResponseEntity.ok(activity);
    }

    @PostMapping
    public ResponseEntity<Activity> create(@RequestBody Activity activity) {
        Activity created = activityService.create(activity);
        URI location = URI.create(String.format("/api/activities/%s", created.getId()));
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> update(@PathVariable Long id, @RequestBody Activity activity) {
        try {
            Activity updated = activityService.update(id, activity);
            return ResponseEntity.ok(updated);
        } catch (java.util.NoSuchElementException ex) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            activityService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (java.util.NoSuchElementException ex) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
