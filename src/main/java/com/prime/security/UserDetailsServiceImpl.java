package com.prime.security;

import com.prime.domain.SystemUser;
import com.prime.repositories.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserRepository.findByEmail(username);

        if(systemUser == null){
            throw new UsernameNotFoundException("email");
        }

        return new UserSS(systemUser.getId(), systemUser.getNome(), systemUser.getEmail(), systemUser.getHashSenha(), systemUser.getPerfis());
    }
}
