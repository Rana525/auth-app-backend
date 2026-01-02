package com.substring.auth.services;

import com.substring.auth.dtos.UserDto;

public interface userService {
//    Create User
    UserDto createUser(UserDto userDto);

//    get user by email
    UserDto getUserByEmail(String email);

//    Update User
    UserDto updateUser(UserDto userDto, String userId);

//    Delete User
    void deleteUser(String userId);

//    Get User by Id
    UserDto getUserById(String userId);

//    Get All User
    Iterable<UserDto> getAllUsers();
}
