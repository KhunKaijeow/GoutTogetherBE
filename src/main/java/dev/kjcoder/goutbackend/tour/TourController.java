package dev.kjcoder.goutbackend.tour;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/tours")
public class TourController {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    private final Map<Integer, Tour> tourInMemdb;

    public TourController() {
        tourInMemdb = new HashMap<>();
    }
    // CRUD - Tour
    // C - Create Tour
    // R - Read, Get 1 tour
    // U - Update on specific record
    // D - Delete from InMemory DB

    // Get All Tour
    @GetMapping
    public List<Tour> getTours() {
        return tourInMemdb.entrySet().stream().map(e -> e.getValue()).toList();
    }

    // Get Tour By ID
    @GetMapping("/{id}")
    public Tour getTourById(@PathVariable int id) {
        return Optional.ofNullable(tourInMemdb.get(id)).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    // Create new tour
    @PostMapping
    public Tour createTour(@RequestBody Tour tour) {
        var newTour = new Tour(ATOMIC_INTEGER.getAndIncrement(), tour.title(), tour.maxPeople());
        var id = newTour.id();
        tourInMemdb.put(id, newTour);
        return tourInMemdb.get(id);
    }

    // Update Tour By ID
    @PutMapping("/{id}")
    public Tour updateTourById(@PathVariable int id, @RequestBody Tour tour) {
        var updateTour = new Tour(id, tour.title(), tour.maxPeople());
        tourInMemdb.put(id, updateTour);
        return tourInMemdb.get(id);
    }

    // Delete Tour By ID
    @DeleteMapping("/{id}")
    public String deleteTourById(@PathVariable int id) {
        if (!tourInMemdb.containsKey(id)) {
            return "Not Found";
        }
        tourInMemdb.remove(id);
        return "Successfully deleted tourId: " + id;
    }

}
