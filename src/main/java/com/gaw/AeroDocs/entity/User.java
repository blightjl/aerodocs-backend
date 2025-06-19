package com.gaw.AeroDocs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @ToString(exclude = "favorites")
public class User {
    @Id
    @NotBlank(message = "Username is required")
    private String username;

    @Column(unique = true)
    @Email(message = "Email must be valid")
    private String email;
}
