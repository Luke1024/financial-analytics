package com.finance.data.service.account;

import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.LoginDto;
import com.finance.data.repository.accounts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean saveUser(User user){
        Optional<User> retrievedUser = userRepository.findUserByEmail(user.getEmail());
        if(retrievedUser.isPresent()){
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    public boolean loginUser(LoginDto loginDto) {
        Optional<User> retrievedUser = userRepository.findUserByEmail(loginDto.getEmail());
        if(retrievedUser.isPresent()){
            if(retrievedUser.get().getPassword().equals(loginDto.getPassword())){

                retrievedUser.get().setUserLoggedIn(true);
                userRepository.save(retrievedUser.get());
                return true;
            } else {
                System.out.println("password incorrect");
                System.out.println(retrievedUser.get().getPassword());
                return false;
            }
        } else {
            System.out.println("user not found");
            return false;
        }
    }

    public boolean logOutUser(Long userId) {
        Optional<User> retrievedUser = userRepository.findById(userId);
        if(retrievedUser.isPresent()){
            retrievedUser.get().setUserLoggedIn(false);
            userRepository.save(retrievedUser.get());
            return true;
        } else return false;
    }

    public boolean changeUserPassword(LoginDto loginDto) {
        Optional<User> retrievedUser = userRepository.findUserByEmail(loginDto.getEmail());
        if(retrievedUser.isPresent()){
            if(retrievedUser.get().getPassword().equals(loginDto.getPassword())){
                retrievedUser.get().setPassword(loginDto.getPassword());
                userRepository.save(retrievedUser.get());
                return true;
            } else {
                System.out.println("password is incorrect");
                return false;
            }
        } else {
            System.out.println("user not found");
            return false;
        }
    }
}
