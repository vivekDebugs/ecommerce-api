package com.example.ecommerceapi.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class Base {
    @Id
    private long id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
}
