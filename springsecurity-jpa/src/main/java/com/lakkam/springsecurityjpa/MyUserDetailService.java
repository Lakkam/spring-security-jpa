package com.lakkam.springsecurityjpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lakkam.springsecurityjpa.models.Users;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Enterd User Not Found in Database!!!");
		}
		return new UserDetails() {

			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return user.getUserName();
			}

			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return user.getPassword();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				authorities.add(new SimpleGrantedAuthority("ROLE_EUSER"));

				return authorities;
			}
		};
	}

}
