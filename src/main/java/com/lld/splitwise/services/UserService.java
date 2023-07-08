package com.lld.splitwise.services;

import com.lld.splitwise.exceptions.UserAlreadyExistsException;
import com.lld.splitwise.exceptions.UserNotFoundException;
import com.lld.splitwise.modles.User;
import com.lld.splitwise.modles.UserStatus;
import com.lld.splitwise.repositories.UserRepository;
import com.lld.splitwise.utill.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String userName, String password, String phoneNumber) throws UserAlreadyExistsException{
        Optional<User> userOptional = userRepository.findByPhone(phoneNumber);

        if (userOptional.isPresent()) {
            if (userOptional.get().getUserStatus().equals(UserStatus.ACTIVE)) {
                throw new UserAlreadyExistsException();
            } else {
                User user = new User();
                user.setUserStatus(UserStatus.ACTIVE);
                user.setName(userName);
                user.setPassword(password);
                return userRepository.save(user);
            }
        }

        User user = new User();
        user.setUserStatus(UserStatus.ACTIVE);
        user.setName(userName);
        user.setPassword(password);
        user.setPhone(phoneNumber);

        return userRepository.save(user);
    }

    // Assuming that username is unique
    // User can update anything name, phonenumber, password
    public User updateProfile(String userName, String filedToUpdate, String newDetail) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByName(userName);

        if (userOptional.isPresent()) {
            User user =  userOptional.get();

            if (filedToUpdate.equalsIgnoreCase(StringConstants.USERNAME)){
                user.setName(newDetail);
            } else if (filedToUpdate.equalsIgnoreCase(StringConstants.PASSWORD)) {
                user.setPassword(newDetail);
            } else if (filedToUpdate.equalsIgnoreCase(StringConstants.PHONE_NUMBER)) {
                user.setPhone(newDetail);
            }

            return userRepository.save(user);
        }
        else{
            throw new UserNotFoundException();
        }
    }
}
