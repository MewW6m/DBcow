package com.dbcow.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "connect")
@Entity
@Where(clause = "deleteFlag=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"username", "createDate", "updateDate", "deleteFlag"})
public class Connect {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connect_id_seq")
	@SequenceGenerator(name = "connect_id_seq", sequenceName = "connect_id_seq", initialValue = 1, allocationSize = 1)
	@JsonProperty(value= "id", index=1, access = Access.READ_ONLY)
	private Integer id;

	@Column(name = "conname", length = 60, nullable = false, unique = true)
	@NotBlank(groups = {  })
	@Size(min = 1, max = 60, groups = {  })
	@JsonProperty(value= "conname", index=2, access = Access.READ_WRITE)
	private String conname;

	@Column(name = "dbtype", nullable = false)
	@NotBlank(groups = {  })
	@JsonProperty(value= "dbtype", index=3, access = Access.READ_WRITE)
	private Integer dbtype;

	@Column(name = "host", length = 60, nullable = false)
	@NotBlank(groups = {  })
	@Size(min = 1, max = 60, groups = {  })
	@JsonProperty(value= "host", index=4, access = Access.READ_WRITE)
	private String host;

	@Column(name = "user", length = 60, nullable = false)
	@NotBlank(groups = {  })
	@Size(min = 1, max = 60, groups = {  })
	@JsonProperty(value= "user", index=5, access = Access.READ_WRITE)
	private String user;

	@Column(name = "password", length = 60, nullable = false)
	@NotBlank(groups = {  })
	@Size(min = 1, max = 60, groups = {  })
	@JsonProperty(value= "password", index=6, access = Access.READ_WRITE)
	private String password;

	@Column(name = "status", nullable = false)
	@NotBlank(groups = {  })
	@JsonProperty(value= "status", index=7, access = Access.READ_WRITE)
	private Integer status;

	@Column(name = "username", length = 60, nullable = false)
	@JsonProperty(value= "username")
	private String username;

	// common
    @Column(name = "createDate", nullable = false)
    @CreationTimestamp
	@JsonProperty(value= "createDate", index=8, access = Access.READ_ONLY)
    protected Date createDate;

	// common
    @Column(name = "updateDate", nullable = false)
    @UpdateTimestamp
	@JsonProperty(value= "updateDate", index=9, access = Access.READ_ONLY)
    protected Date updateDate;

	// common
    @Column(name = "deleteFlag", insertable=false, columnDefinition = "bit(1) NOT NULL default 0")
	@JsonProperty(value= "deleteFlag", index=10, access = Access.READ_ONLY)
    protected Boolean deleteFlag;
}
