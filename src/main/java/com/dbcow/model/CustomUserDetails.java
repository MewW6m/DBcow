package com.dbcow.model;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails extends CommonEntity implements UserDetails {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_user_id_seq")
    @SequenceGenerator(
        name = "user_user_id_seq",
        sequenceName = "user_user_id_seq",
        initialValue = 1,
        allocationSize = 1)
    private Integer id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "password", length = 60, nullable = false) //←２
    private String password;

    @Column(name = "roles", length = 60, nullable = false) //←３
    protected String roles;

    @Column(name = "enableFlag", nullable = false) //←４
    private Boolean enableFlag;

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Stream.of(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
	}

}
