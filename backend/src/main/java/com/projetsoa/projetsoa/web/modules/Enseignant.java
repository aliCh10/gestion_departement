package com.projetsoa.projetsoa.web.modules;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "Enseignants")
@Entity
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "is required")
    private String nom;
    @NotBlank(message = "is required")
    private String prenom;
    @NotBlank(message = "is required")
    @Length(min = 8 , max = 8, message = "length cin incorrect")
    private String cin;
    @NotBlank(message = "is required")
    @Email(message = "invalid email format")
    private String email;
    @NotBlank(message = "is required")
    private String adresse;
    @NotBlank(message = "is required")
    @Length(min = 8,max = 12)
    private String phoneNumber;
    @NotBlank(message = "is required")
    private String matière;

}
