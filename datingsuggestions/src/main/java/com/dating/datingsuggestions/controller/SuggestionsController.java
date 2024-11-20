package com.dating.datingsuggestions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dating.datingsuggestions.model.User;
import com.dating.datingsuggestions.service.SuggestionsService;
import com.dating.datingsuggestions.service.SuggestionsService2;

@RestController
public class SuggestionsController {	
	
	@Autowired
    private SuggestionsService suggestionService;
	
	@Autowired
    private SuggestionsService2 suggestionService2;

    @GetMapping("/datingapp/{userId}/topmatches/{topN}")
    public List<User> getTopMatches(@PathVariable Long userId, @PathVariable int topN) {
        // Assuming users are fetched from DB or hardcoded list
        //List<User> allUsers = getAllUsers(); // Replace with actual DB call if needed
    	List<User> allUsers = suggestionService.getAllUsers();
        User targetUser = allUsers.stream().filter(u -> u.getId().equals(userId)).findFirst().orElse(null);

        if (targetUser != null) {
            //return suggestionService.getTopMatches(targetUser, allUsers, topN);
            return suggestionService.getTopMatches2(targetUser, allUsers, topN);
            //return suggestionService2.getSuggestions(targetUser, allUsers);
        } else {
            throw new RuntimeException("User not found!");
        }
    }

    // Placeholder method to simulate fetching all users
    private List<User> getAllUsers() {
        // Ideally, this should be a DB call, but for now, we'll return a static list
        return List.of(
                new User(1L, "User 1", "Female", 25, List.of("Cricket", "Chess")),
                new User(2L, "User 2", "Male", 27, List.of("Cricket", "Football", "Movies")),
                new User(3L, "User 3", "Male", 26, List.of("Movies", "Tennis", "Football", "Cricket")),
                new User(4L, "User 4", "Female", 24, List.of("Tennis", "Football", "Badminton")),
                new User(5L, "User 5", "Female", 32, List.of("Cricket", "Football", "Movies", "Badminton"))
        );
    }
	
	

}
