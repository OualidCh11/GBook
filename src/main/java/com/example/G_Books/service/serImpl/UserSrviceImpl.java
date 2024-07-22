package com.example.G_Books.service.serImpl;

import com.example.G_Books.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSrviceImpl implements UserDetailsService {



    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userDao.findByEmail(userEmail).orElseThrow(() ->
                new UsernameNotFoundException("user not found"));
    }
}
