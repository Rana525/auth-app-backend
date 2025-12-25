package com.substring.auth.services;
import com.substring.auth.dtos.UserDto;
import com.substring.auth.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements userService{

    private final UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public UserDto deleteUser(String userId) {
        return null;
    }

    @Override
    public UserDto getUserById(String userId) {
        return null;
    }
}
