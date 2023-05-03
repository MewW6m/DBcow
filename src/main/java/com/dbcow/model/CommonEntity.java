package com.dbcow.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonEntity {

    @Column(name = "createUserId", nullable = false)
    private Integer createUserId = 1;

    @Column(name = "createUserDate", nullable = false)
    @CreationTimestamp
    private Date createUserDate;

    @Column(name = "updateUserId", nullable = false)
    private Integer updateUserId = 1;

    @Column(name = "updateUserDate", nullable = false)
    @CreationTimestamp
    private Date updateUserDate;

    @Column(name = "deleteFlag", nullable = false)
    private Boolean deleteFlag = false;
    
}
