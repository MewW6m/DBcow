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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "setting")
@Entity
@Where(clause = "deleteFlag=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"username", "createDate", "updateDate", "deleteFlag"})
public class Setting {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setting_id_seq")
	@SequenceGenerator(name = "setting_id_seq", sequenceName = "setting_id_seq", initialValue = 1, allocationSize = 1)
	@JsonProperty(value= "id", index=1, access = Access.READ_ONLY)
	private Integer id;

	@Column(name = "username", length = 60, nullable = false)
	@JsonProperty(value= "username")
	private String username;

	@Column(name = "name", length = 200, nullable = false)
	@JsonProperty(value= "name", index=3, access = Access.READ_ONLY)
	private String name;

	@Column(name = "value", length = 60, nullable = true)
	// @Null(groups = {  })
	// @NotBlank(groups = {  })
	@JsonProperty(value= "value", index=4, access = Access.READ_WRITE)
	private String value;

	@Column(name = "type", length = 60, nullable = false)
	@JsonProperty(value= "type", index=5, access = Access.READ_ONLY)
	protected String type;

	@Column(name = "candidate", length = 200, nullable = true)
	@JsonProperty(value= "candidate", index=6, access = Access.READ_ONLY)
	protected String candidate;

	// common
    @Column(name = "createDate", nullable = false)
    @CreationTimestamp
	@JsonProperty(value= "createDate", index=7, access = Access.READ_ONLY)
    protected Date createDate;

	// common
    @Column(name = "updateDate", nullable = false)
    @UpdateTimestamp
	@JsonProperty(value= "updateDate", index=8, access = Access.READ_ONLY)
    protected Date updateDate;

	// common
    @Column(name = "deleteFlag", insertable=false, columnDefinition = "bit(1) NOT NULL default 0")
	@JsonProperty(value= "deleteFlag", index=9, access = Access.READ_ONLY)
    protected Boolean deleteFlag;
}
