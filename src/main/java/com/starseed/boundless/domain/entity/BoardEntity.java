package com.starseed.boundless.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BoardEntity {
    @Id
    private long id;
    private String name;
}
