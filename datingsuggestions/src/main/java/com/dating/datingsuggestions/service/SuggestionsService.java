package com.dating.datingsuggestions.service;

import java.util.ArrayList;
import java.util.Arrays;
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
		List<User> newUserList = new ArrayList<>();
		List<User> allUsers = userRepository.findAll();
		User newUser;
		List<String> interestsList ;

		for (User user : allUsers) {
		    newUser = new User();
		    newUser.setId(user.getId());
		    newUser.setName(user.getName());
		    newUser.setGender(user.getGender());
		    newUser.setAge(user.getAge());

		    // Copy interests, if available
		    if (user.getInterests() != null && !user.getInterests().isEmpty()) {
		    	interestsList = new ArrayList<>();		    	
			    	interestsList = Arrays.asList(user.getInterests().toString().split(","));
			        interestsList = interestsList.stream()
			            .map(String::trim)  // Clean up any unnecessary spaces
			            .collect(Collectors.toList());
			        newUser.setInterests(interestsList);		    	
		    }

		    newUserList.add(newUser);
		}		
		return newUserList;
	} 
    
	public List<User> getTopMatches(User user, List<User> users, int topN) {
		//First filter the list from opposite gender
		List<User> oppositeGenderUsers = users.stream().filter(u -> !u.getGender().equals(user.getGender()))
				.collect(Collectors.toList());
		//Then apply the sorting based on Matching age first and then based on Interests
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
