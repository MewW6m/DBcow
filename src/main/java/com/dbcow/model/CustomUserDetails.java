package com.dbcow.model;

import java.sql.Date;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dbcow.config.ViewGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
@JsonIgnoreProperties({"id", "accountNonExpired", "accountNonLocked", 
	"credentialsNonExpired", "enabled", "authorities"})
public class CustomUserDetails implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_user_id_seq")
	@SequenceGenerator(name = "user_user_id_seq", sequenceName = "user_user_id_seq", initialValue = 1, allocationSize = 1)
	@Null(groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	private Integer id;

	@Column(name = "username", length = 60, nullable = false, unique=true)
	@NotBlank(groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@Size(min = 4, max = 20, groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@Pattern(regexp = "^[a-zA-Z0-9!-/:-@\\[-`{-~ ]*$", groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@JsonProperty(value= "username", index=1, access = Access.READ_WRITE)
	private String username;

	@Column(name = "password", length = 60, nullable = false)
	@NotBlank(groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@Size(min = 4, max = 20, groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@Pattern(regexp = "^[a-zA-Z0-9!-/:-@\\[-`{-~ ]*$", groups = { ViewGroup.PostUser.class, ViewGroup.PatchUser.class })
	@JsonProperty(value= "password", access = Access.WRITE_ONLY)
	private String password;

	@Column(name = "roles", length = 60, nullable = false)
	@Null(groups = { ViewGroup.PostUser.class })
	@NotBlank(groups = { ViewGroup.PatchUser.class })
	@Pattern(regexp = "^ROLE_[a-zA-Z0-9]*$", groups = { ViewGroup.PatchUser.class })
	@JsonProperty(value= "roles", index=2, access = Access.READ_WRITE)
	protected String roles;

	// common
    @Column(name = "createDate", nullable = false)
    @CreationTimestamp
	@JsonProperty(value= "createDate", index=4, access = Access.READ_ONLY)
    protected Date createDate;

	// common
    @Column(name = "updateDate", nullable = false)
    @UpdateTimestamp
	@JsonProperty(value= "updateDate", index=5, access = Access.READ_ONLY)
    protected Date updateDate;

	// common
    @Column(name = "deleteFlag", insertable=false, columnDefinition = "bit(1) NOT NULL default 0")
	@JsonProperty(value= "deleteFlag", index=3, access = Access.READ_ONLY)
    protected Boolean deleteFlag;

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
