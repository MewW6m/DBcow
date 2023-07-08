package com.dbcow.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@JsonIgnoreProperties({"id", "username", "conecctString", "createDate", "updateDate", "deleteFlag"})
public class Connect {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connect_id_seq")
	@SequenceGenerator(name = "connect_id_seq", sequenceName = "connect_id_seq", initialValue = 1, allocationSize = 1)
	@JsonProperty(value= "id", index=1, access = Access.READ_ONLY)
	private Integer id;

	@Column(name = "conname", length = 60, nullable = false, unique = true)
	@NotBlank(groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@Size(min = 1, max = 60, groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@Pattern(regexp = "^[a-zA-Z0-9!-/:-@\\[-`{-~ ]*$", groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@JsonProperty(value= "conname", index=2, access = Access.READ_WRITE)
	private String conname;

	@Column(name = "dbtype", nullable = false)
	@NotNull(groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@JsonProperty(value= "dbtype", index=3, access = Access.READ_WRITE)
	private Integer dbtype;

	@Column(name = "host", length = 60, nullable = false)
	@NotBlank(groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@Size(min = 1, max = 60, groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@JsonProperty(value= "host", index=4, access = Access.READ_WRITE)
	private String host;

	@Column(name = "user", length = 60, nullable = false)
	@NotBlank(groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@Size(min = 1, max = 60, groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@JsonProperty(value= "user", index=5, access = Access.READ_WRITE)
	private String user;

	@Column(name = "password", length = 60, nullable = false)
	@NotBlank(groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@Size(min = 1, max = 60, groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@JsonProperty(value= "password", index=6, access = Access.READ_WRITE)
	private String password;

	@Column(name = "status", nullable = false)
	@NotNull(groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@JsonProperty(value= "status", index=7, access = Access.READ_WRITE)
	private Integer status;

	@Column(name = "username", length = 60, nullable = false)
	@JsonProperty(value= "username", index=8, access = Access.READ_ONLY)
	private String username;

    @Column(name = "dataregistflag", nullable = false)
	@NotNull(groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@JsonProperty(value= "dataregistflag", index=10, access = Access.READ_WRITE)
    private Boolean dataregistflag;

    @Column(name = "dataupdateflag", nullable = false)
	@NotNull(groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@JsonProperty(value= "dataupdateflag", index=11, access = Access.READ_WRITE)
    private Boolean dataupdateflag;

    @Column(name = "datadeleteflag", nullable = false)
	@NotNull(groups = { ViewGroup.PostConnect.class, ViewGroup.PatchConnect.class })
	@JsonProperty(value= "datadeleteflag", index=12, access = Access.READ_WRITE)
    private Boolean datadeleteflag;

	@Column(name = "connectstring", nullable = false)
	@JsonProperty(value= "connectstring", index=13, access = Access.READ_ONLY)
    private String connectstring;

	// common
    @Column(name = "createDate", nullable = false)
    @CreationTimestamp
	@JsonProperty(value= "createDate", index=97, access = Access.READ_ONLY)
    protected Date createDate;

	// common
    @Column(name = "updateDate", nullable = false)
    @UpdateTimestamp
	@JsonProperty(value= "updateDate", index=98, access = Access.READ_ONLY)
    protected Date updateDate;

	// common
    @Column(name = "deleteFlag", insertable=false, columnDefinition = "bit(1) NOT NULL default 0")
	@JsonProperty(value= "deleteFlag", index=99, access = Access.READ_ONLY)
    protected Boolean deleteFlag;
}
