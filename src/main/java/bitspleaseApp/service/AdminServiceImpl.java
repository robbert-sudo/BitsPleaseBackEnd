package bitspleaseApp.service;

import bitspleaseApp.dto.response.UserDetailsResponse;
import bitspleaseApp.model.Authority;
import bitspleaseApp.model.Game;
import bitspleaseApp.model.User;
import bitspleaseApp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminServiceImpl() {

    }

    @Override
    public Set<UserDetailsResponse> findAllByDisabled() {
        long user_id = 0;
        String username = null;
        boolean enabled = false;
        String email = null;
        Set<Authority> authorities = null;
        Set<Game> games = null;
        Set<UserDetailsResponse> userDetailsResponses = new HashSet<>();


        Iterable<User> users = adminRepository.findAllByEnabled(false);
        for (User user : users) {
            user_id = user.getUser_id();
            username = user.getUsername();
            enabled = user.isEnabled();
            email = user.getEmail();
            authorities = user.getAuthorities();
            games = user.getGames();
            UserDetailsResponse userDetailsResponse = new UserDetailsResponse(user_id, username, enabled, email, authorities, games);
            userDetailsResponses.add(userDetailsResponse);
        }
        return userDetailsResponses;
    }


    @Override
    public void delete(String username) {
        Optional<User> possibleUser = adminRepository.findByUsername(username);
        if (possibleUser.isPresent()) {
            User user = possibleUser.get();
            adminRepository.delete(user);
        }
    }




}
