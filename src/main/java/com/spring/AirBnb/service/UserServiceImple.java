package com.spring.AirBnb.service;

import com.spring.AirBnb.entity.Enums.Role;
import com.spring.AirBnb.entity.User;
import com.spring.AirBnb.exception.ResourceNotFoundException;
import com.spring.AirBnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService, UserDetailsService {
    private final UserRepository userRepository;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }

}
