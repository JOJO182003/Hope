package Hope.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @Column(name = "password", nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


/*
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Le nom d'utilisateur est obligatoire.")
    @Size(max = 50, message = "Le nom d'utilisateur ne peut pas dépasser 50 caractères.")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Le prénom est obligatoire.")
    @Size(max = 100, message = "Le prénom ne peut pas dépasser 100 caractères.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Le nom de famille est obligatoire.")
    @Size(max = 100, message = "Le nom de famille ne peut pas dépasser 100 caractères.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Le rôle est obligatoire.")
    @Size(max = 50, message = "Le rôle ne peut pas dépasser 50 caractères.")
    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères.")
    @Column(name = "password", nullable = false)
    private String password;

    // Getters et Setters
}
*/
