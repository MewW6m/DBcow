package com.dbcow.model;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dbcow.config.ViewGroup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Entity
@Where(clause = "deleteFlag=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails extends CommonEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_user_id_seq")
	@SequenceGenerator(name = "user_user_id_seq", sequenceName = "user_user_id_seq", initialValue = 1, allocationSize = 1)
	@Null(groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	private Integer id;

	@Column(name = "username", length = 60, nullable = false, unique=true)
	@NotBlank(groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@Size(min = 4, max = 20, groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@Pattern(regexp = "^[a-zA-Z0-9!-/:-@\\[-`{-~ ]*$", groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	private String username;

	@Column(name = "password", length = 60, nullable = false)
	@NotBlank(groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@Size(min = 4, max = 20, groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@Pattern(regexp = "^[a-zA-Z0-9!-/:-@\\[-`{-~ ]*$", groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	private String password;

	@Column(name = "roles", length = 60, nullable = false)
	@Null(groups = { ViewGroup.PostUser.class })
	@NotBlank(groups = { ViewGroup.PatchUser.class })
	@Pattern(regexp = "^ROLE_[a-zA-Z0-9]*$", groups = { ViewGroup.PatchUser.class })
	protected String roles;

	@Override
	public String getUsername() {
		return username;
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
