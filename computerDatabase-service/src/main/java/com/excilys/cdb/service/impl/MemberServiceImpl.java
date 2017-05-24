package com.excilys.cdb.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Role;
import com.excilys.cdb.model.User;
import com.excilys.cdb.persistence.UserDAO;

@Service
public class MemberServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {

        Optional<User> member = userDAO.find(arg0);

        if (!member.isPresent()) {
            throw new UsernameNotFoundException("user not found");
        }

        User user = member.get();
        LOGGER.debug("test = " + user.getRole());
        HashSet<GrantedAuthority> h = new HashSet<GrantedAuthority>();

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role r : user.getRole())
            authorities.add(new SimpleGrantedAuthority(r.getRole().toUpperCase()));
        
        LOGGER.debug("test2 = " + authorities);

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }

}
