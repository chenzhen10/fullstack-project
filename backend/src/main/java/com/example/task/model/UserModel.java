package com.example.task.model;

import com.example.task.model.type.Role;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Column(name = "role" , columnDefinition="ENUM('ADMIN','VIEWER')")
    @Enumerated(EnumType.STRING)
    private Role role;
}
