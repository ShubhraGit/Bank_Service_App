/*
 * package com.hs;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.provisioning.InMemoryUserDetailsManager; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.config.Customizer; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import org.springframework.security.core.userdetails.User;
 * 
 * @Configuration
 * 
 * public class SpringConfig {
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Bean public UserDetailsService userDetailsService() { UserDetails admin =
 * User.builder(). username("shubh").
 * password(passwordEncoder().encode("1234")). roles("ADMIN"). build();
 * 
 * 
 * UserDetails user = User.builder(). username("mishtu").
 * password(passwordEncoder().encode("1234")). roles("USER"). build();
 * 
 * return new InMemoryUserDetailsManager(admin,user);
 * 
 * }
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity
 * httpSecurity) throws Exception { httpSecurity.authorizeHttpRequests(auth
 * ->auth.requestMatchers(HttpMethod.GET).hasRole("ADMIN")
 * .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
 * .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
 * .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
 * .anyRequest().authenticated()) .formLogin(form -> form.permitAll())
 * .logout(lg ->lg.permitAll());
 * 
 * 
 * return httpSecurity.csrf(csrf -> csrf.disable()).
 * authorizeHttpRequests(authorize ->
 * authorize.requestMatchers(HttpMethod.GET).hasRole("USER")
 * .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
 * .requestMatchers(HttpMethod.PUT).hasRole("USER")
 * .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
 * .anyRequest().authenticated()) .httpBasic(Customizer.withDefaults())
 * .build();
 * 
 * 
 * 
 * httpSecurity.authorizeHttpRequests(auth
 * ->auth.requestMatchers("/register").hasRole("ADMIN").anyRequest().
 * authenticated()) .formLogin(form -> form.permitAll()) .logout(lg
 * ->lg.permitAll());
 * 
 * httpSecurity.authorizeHttpRequests(auth
 * ->auth.requestMatchers("/deposit").hasRole("ADMIN").anyRequest().
 * authenticated()) .formLogin(form -> form.permitAll()) .logout(lg
 * ->lg.permitAll());
 * 
 * httpSecurity.authorizeHttpRequests(auth
 * ->auth.requestMatchers("/delete/*.*").hasRole("ADMIN").anyRequest().
 * authenticated()) .formLogin(form -> form.permitAll()) .logout(lg
 * ->lg.permitAll());
 * 
 * httpSecurity.authorizeHttpRequests(auth
 * ->auth.requestMatchers("/withdraw").hasRole("ADMIN").anyRequest().
 * authenticated()) .formLogin(form -> form.permitAll()) .logout(lg
 * ->lg.permitAll());
 * 
 * httpSecurity.authorizeHttpRequests(auth
 * ->auth.requestMatchers("/bankStatement").hasRole("ADMIN").anyRequest().
 * authenticated()) .formLogin(form -> form.permitAll()) .logout(lg
 * ->lg.permitAll());
 * 
 * return httpSecurity.build();
 * 
 * 
 * }
 * 
 * }
 */