package com.dating.datingsuggestions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dating.datingsuggestions.model.User;
import com.dating.datingsuggestions.service.SuggestionsService;

@SpringBootTest
class DatingsuggestionsApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
    private SuggestionsService suggestionService;

    @Test
    public void testGetTopMatches() {
        // Sample data setup
        List<User> allUsers = List.of(
                new User(1L, "User 1", "Female", 25, List.of("Cricket", "Chess")),
                new User(2L, "User 2", "Male", 27, List.of("Cricket", "Football", "Movies")),
                new User(3L, "User 3", "Male", 26, List.of("Movies", "Tennis", "Football", "Cricket")),
                new User(4L, "User 4", "Female", 24, List.of("Tennis", "Football", "Badminton")),
                new User(5L, "User 5", "Female", 32, List.of("Cricket", "Football", "Movies", "Badminton"))
        );
        User targetUser = new User(2L, "User 2", "Male", 27, List.of("Cricket", "Football", "Movies"));

        //List<User> topMatches = suggestionService.getTopMatches(targetUser, allUsers, 2);
        List<User> topMatches = suggestionService.getTopMatches(targetUser, allUsers, 2);

        // Expected top 2 matches based on the problem's example
        assertEquals(2, topMatches.size());
        assertEquals("User 1", topMatches.get(0).getName());
        assertEquals("User 4", topMatches.get(1).getName());
    }

}
