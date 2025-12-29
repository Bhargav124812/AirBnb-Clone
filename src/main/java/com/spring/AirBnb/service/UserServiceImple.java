package com.spring.AirBnb.service;

import com.spring.AirBnb.dto.ProfileUpdateRequestDto;
import com.spring.AirBnb.dto.UserDto;
import com.spring.AirBnb.entity.Enums.Role;
import com.spring.AirBnb.entity.User;
import com.spring.AirBnb.exception.ResourceNotFoundException;
import com.spring.AirBnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.spring.AirBnb.util.AppUtils.getCurrentUser;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImple implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+id));
    }

    @Override
    public void updateTheUserRole(String role) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (role.equals("guest")) {
            user.setRoles(Set.of(Role.GUEST));
        } else {
            user.setRoles(Set.of(Role.HOTEL_MANAGER));
        }
        userRepository.save(user);
    }

    @Override
    public void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto) {
        User user = getCurrentUser();

        if(profileUpdateRequestDto.getDateOfBirth() != null) user.setDateOfBirth(profileUpdateRequestDto.getDateOfBirth());
        if(profileUpdateRequestDto.getGender() != null) user.setGender(profileUpdateRequestDto.getGender());
        if (profileUpdateRequestDto.getName() != null) user.setName(profileUpdateRequestDto.getName());

        userRepository.save(user);
    }

    @Override
    public UserDto getMyProfile() {
        User user = getCurrentUser();
        log.info("Getting the profile for user with id: {}", user.getId());
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }

}
