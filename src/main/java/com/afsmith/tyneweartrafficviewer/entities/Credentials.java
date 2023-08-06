package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents user login credentials.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Credentials {
    @Column(unique = true)
    private String username;
    private String password;
}
