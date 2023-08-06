package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A data repository, managing access to user data.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their usernames. Since usernames are unique, this will always
     * find a single user.
     * @param username The username to search for.
     * @return The data for the user.
     */
    User findUserByCredentialsUsername(String username);

    /**
     * Find a user by their authentication token.
     * @param token The authentication token to search for.
     * @return The found user data.
     */
    User findUserByToken(String token);
}
