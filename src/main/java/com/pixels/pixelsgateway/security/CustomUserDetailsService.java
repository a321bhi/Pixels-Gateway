//package com.pixels.pixelsgateway.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.pixels.pixelsgateway.doa.UserRepository;
//import com.pixels.pixelsgateway.model.PixelSenseUser;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//		PixelSenseUser pixelSenseUser = userRepository.findForAuth(userName);
//		return new UserDetailsImplementation(pixelSenseUser);
//	}
//
//}
