package com.lld.splitwise.services;

import com.lld.splitwise.dtos.GroupResponseDto;
import com.lld.splitwise.exceptions.UserNotFoundException;
import com.lld.splitwise.modles.Group;
import com.lld.splitwise.modles.User;
import com.lld.splitwise.repositories.GroupRepository;
import com.lld.splitwise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {

    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }
    public Group addGroup(String createdBy, String groupName) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByName(createdBy);
        Group group = new Group();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            group.setName(groupName);
            group.setCreatedBy(user);

            return groupRepository.save(group);
        }
        else {
            throw new UserNotFoundException();
        }
    }

}
