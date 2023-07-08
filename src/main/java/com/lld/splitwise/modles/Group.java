package com.lld.splitwise.modles;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "groups")
public class Group extends BaseModel{
    private String description;
    private String name;

    @ManyToOne
    private User createdBy;

    @ManyToMany
    private List<User> members;
}
