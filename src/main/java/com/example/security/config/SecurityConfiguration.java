//package com.example.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(r ->
//                        r.requestMatchers("/base/get").authenticated())
//                .authorizeHttpRequests(r->
//                        r.requestMatchers("/base/post")
//                                .permitAll()).
//                httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public UserDetailsService userDetailsService(){
////        UserDetails uts = User.withUsername("Sagar").password(passwordEncoder().encode("Sagar@123455"))
////                .roles("ROLE-ADMIN").build();
////        UserDetails uts1 = User.withUsername("Amit").password(passwordEncoder().encode("Amint@1998"))
////                .build();
////        return new InMemoryUserDetailsManager(uts,uts1);
////
////    }
//
//}
