package com.substring.auth.services;
import com.substring.auth.dtos.UserDto;
import com.substring.auth.entites.Provider;
import com.substring.auth.entites.Users;
import com.substring.auth.exceptions.ResourceNotFoundExceptions;
import com.substring.auth.helpers.UserHelper;
import com.substring.auth.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements userService{
    @Autowired
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (userRepo.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Users user=modelMapper.map(userDto, Users.class);
        user.setProvider(userDto.getProvider()!=null?userDto.getProvider(): Provider.LOCAL);
        Users saveduser=userRepo.save(user);
        return modelMapper.map(saveduser, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        Users user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundExceptions("User Not Found !!!"));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        UUID uid=UserHelper.parseUUID(userId);
        Users existingUser = userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundExceptions("User Not Found !!!"));
        if (userDto.getName()!=null) existingUser.setName(userDto.getName());
        if (userDto.getImage()!=null) existingUser.setImage(userDto.getImage());
        if (userDto.getProvider()!=null) existingUser.setProvider(userDto.getProvider());
//      TODO: change the password updation logic.....
        if (userDto.getPassword()!=null) existingUser.setPassword(userDto.getPassword());
        existingUser.setEnable(userDto.isEnable());
        existingUser.setUpdatedAt(Instant.now());
        Users updatedUser = userRepo.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
       UUID uid=  UserHelper.parseUUID(userId);
        Users user = userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundExceptions("User Not Found !!!"));
        userRepo.delete(user);

    }

    @Override
    public UserDto getUserById(String userId) {
        Users user = userRepo.findById(UserHelper.parseUUID(userId)).orElseThrow(() -> new ResourceNotFoundExceptions("User Not Found !!!"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public Iterable<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map( user -> modelMapper.map(user,UserDto.class)).toList();
    }
}
