package com.dating.datingsuggestions.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dating.datingsuggestions.model.User;

@Service
public class SuggestionsService2 {
	

	public List<User> getSuggestions(User currentUser, List<User> allUsers) {
        List<User> potentialMatches = new ArrayList<>();

        // Gender Preference: Fetch opposite gender users
        for (User user : allUsers) {
            if (!user.getGender().equals(currentUser.getGender())) {
                potentialMatches.add(user);
            }
        }

        // Sorting based on age, interests, and gender
        potentialMatches.sort((user1, user2) -> {
            int score1 = calculateMatchScore(currentUser, user1);
            int score2 = calculateMatchScore(currentUser, user2);
            return Integer.compare(score2, score1); // Higher score comes first
        });

        return potentialMatches;
    }

    private int calculateMatchScore(User currentUser, User candidate) {
        int score = 0;

        // Age Rule: Closer age gives higher score
        int ageDifference = Math.abs(currentUser.getAge() - candidate.getAge());
        score += 100 - ageDifference; // Giving higher weight to age match

        // Interest Rule: Number of matching interests
        long interestMatchCount = currentUser.getInterests().stream()
                                              .filter(candidate.getInterests()::contains)
                                              .count();
        score += interestMatchCount * 10; // Giving 10 points per matching interest

        return score;
    }
}
