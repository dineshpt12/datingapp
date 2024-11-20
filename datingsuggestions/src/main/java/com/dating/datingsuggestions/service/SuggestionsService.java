package com.dating.datingsuggestions.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.datingsuggestions.model.User;
import com.dating.datingsuggestions.repository.UserRepository;

@Service
public class SuggestionsService {
	

	@Autowired
    private UserRepository userRepository;
	
	public List<User> getAllUsers()
	{
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	}
	

    private int calculateInterestMatch(User user1, User user2) {
        List<String> commonInterests = user1.getInterests().stream()
                .filter(user2.getInterests()::contains)
                .collect(Collectors.toList());
        return commonInterests.size();
    }

    // Method to rank users based on gender, age, and interest match
    public List<User> getTopMatches(User targetUser, List<User> allUsers, int topN) {
        // Sort based on gender preference (opposite gender first)
        Comparator<User> comparator = Comparator
                .comparing((User u) -> !u.getGender().equals(targetUser.getGender()))
                .thenComparingInt(u -> Math.abs(u.getAge() - targetUser.getAge())); // Sort by age proximity
                //.thenComparingInt(u -> calculateInterestMatch(u, targetUser)); // Sort by interest match
        
        return allUsers.stream()
                .filter(user -> !user.getId().equals(targetUser.getId())) // Exclude the target user
                .sorted(comparator.reversed()) // Reverse order to prioritize the best matches
                //.limit(topN) // Get top N matches
                .collect(Collectors.toList());
    }
    
	public List<User> getTopMatches2(User user, List<User> users, int topN) {
		List<User> oppositeGenderUsers = users.stream().filter(u -> !u.getGender().equals(user.getGender()))
				.collect(Collectors.toList());
		oppositeGenderUsers.sort((u1, u2) -> {
			int ageDifference1 = Math.abs(u1.getAge() - user.getAge());
			int ageDifference2 = Math.abs(u2.getAge() - user.getAge());
			long commonInterests1 = u1.getInterests().stream().filter(user.getInterests()::contains).count();
			long commonInterests2 = u2.getInterests().stream().filter(user.getInterests()::contains).count();
			if (ageDifference1 != ageDifference2) {
				return Integer.compare(ageDifference1, ageDifference2);
			} else {
				return Long.compare(commonInterests2, commonInterests1);
			}
		});
		return oppositeGenderUsers.stream().limit(topN).collect(Collectors.toList());
	}
}
