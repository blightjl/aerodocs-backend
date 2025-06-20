package com.gaw.AeroDocs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "username")
// @ToString(exclude = "favorites")
public class User {
    @Id
    @NotBlank(message = "Username is required")
    private String username;

    @Column(unique = true)
    @Email(message = "Email must be valid")
    private String email;

    @ManyToMany
    @JoinTable(
        name = "user_favorite_models",
        joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
        inverseJoinColumns = @JoinColumn(name = "full_model_name", referencedColumnName = "full_model_name")
    )
    private Set<AircraftModel> favoriteAircraftModels;

    public void addFavoriteAircraftModel(AircraftModel model) {
        if (this.favoriteAircraftModels == null) {
            this.favoriteAircraftModels = new HashSet<>();
        }
        if (this.favoriteAircraftModels.add(model)) {
            if (model.getFavoritedByUsers() == null) {
                model.setFavoritedByUsers(new HashSet<>());
            }
            model.getFavoritedByUsers().add(this);
        }
    }

    public void removeFavoriteAircraftModel(AircraftModel model) {
        if (this.favoriteAircraftModels != null && this.favoriteAircraftModels.remove(model)) {
            if (model.getFavoritedByUsers() != null) {
                model.getFavoritedByUsers().remove(this);
            }
        }
    }
}
