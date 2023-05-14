package com.dbcow.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class CommonEntity {

    @Column(name = "createDate", nullable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "updateDate", nullable = false)
    @UpdateTimestamp
    private Date updateDate;

    @Column(name = "deleteFlag", insertable=false, columnDefinition = "bit(1) NOT NULL default 0")
    private Boolean deleteFlag;

}
