package com.finance.service.database;

import com.finance.domain.User;
import com.finance.domain.dto.LoginDto;
import com.finance.domain.dto.PasswordChangerDto;
import com.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private Logger logger = Logger.getLogger(UserService.class.getName());

    public User getUserById(Long userId){
        Optional<User> retrievedUser = userRepository.findById(userId);
        if(retrievedUser.isPresent()){
            return retrievedUser.get();
        } else {
            logger.log(Level.INFO,"user not found");
            return new User();
        }
    }

    public boolean saveUser(User user){
        Optional<User> retrievedUser = userRepository.findUserByEmail(user.getEmail());
        if(retrievedUser.isPresent()){
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    public User retrieveUserByEmail(String email){
        Optional<User> userRetrieved = userRepository.findUserByEmail(email);
        if(userRetrieved.isPresent()){
            return userRetrieved.get();
        } else {
            logger.log(Level.INFO,"User not found");
            return new User();
        }
    }

    public User loginUser(LoginDto loginDto) {
        Optional<User> retrievedUser = userRepository.findUserByEmail(loginDto.getEmail());
        if(retrievedUser.isPresent()){
            if(retrievedUser.get().getPassword().equals(loginDto.getPassword())){
                retrievedUser.get().setUserLoggedIn(true);
                userRepository.save(retrievedUser.get());
                return userRepository.findUserByEmail(loginDto.getEmail()).get();
            } else {
                logger.log(Level.INFO,"password incorrect");
                return new User();
            }
        } else {
            logger.log(Level.INFO,"user not found");
            return new User();
        }
    }

    public User logOutUser(Long userId) {
        Optional<User> retrievedUser = userRepository.findById(userId);
        if(retrievedUser.isPresent()){
            retrievedUser.get().setUserLoggedIn(false);
            return userRepository.save(retrievedUser.get());
        } else {
            logger.log(Level.INFO, "user not found");
            return new User();
        }
    }

    public User changeUserPassword(PasswordChangerDto passwordChangerDto) {
        Optional<User> retrievedUser = userRepository.findUserByEmail(passwordChangerDto.getEmail());
        if(retrievedUser.isPresent()){
            if(retrievedUser.get().getPassword().equals(passwordChangerDto.getPassword())){
                retrievedUser.get().setPassword(passwordChangerDto.getNewPassword());
                return userRepository.save(retrievedUser.get());
            } else {
                logger.log(Level.INFO,"password is incorrect");
                return new User();
            }
        } else {
            logger.log(Level.INFO,"user not found");
            return new User();
        }
    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
}
