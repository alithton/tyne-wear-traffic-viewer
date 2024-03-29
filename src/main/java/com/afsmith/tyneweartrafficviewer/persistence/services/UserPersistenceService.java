package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.exceptions.UserAlreadyExistsException;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Service providing access to user data persistence operations.
 */
@Getter
@RequiredArgsConstructor
@Service
public class UserPersistenceService {
    private final UserRepository repository;

    /**
     * Attempt to find a user with the provided username. If no such
     * user is found, returns null.
     * @param username The user's username.
     * @return The user with credentials matching those provided, or null.
     */
    public User find(String username) {
        return repository.findUserByCredentialsUsername(username);
    }

    /**
     * Attempt to find a user with the provided token. If no such user is found,
     * returns null.
     * @param token The user's token.
     * @return The user with matching token, or null.
     */
    public User findByToken(String token) {
        return repository.findUserByToken(token);
    }

    /**
     * Save the provided user details in the database. If a user with identical
     * username and password is already present, throws an error.
     * @param user The user to be saved.
     * @throws UserAlreadyExistsException Thrown if a user with the same username and password
     * is already present in the database.
     */
    public void save(User user) throws UserAlreadyExistsException {
        try {
            repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("User already in database.", e);
        }
    }

    /**
     * Update the details of a user that is already persisted in the database. Throws an error
     * if the user does not exist.
     * @param user The user to be updated.
     */
    public void update(User user) {
        // Relies on the fact that user IDs are generated by the database.
        if (user.getId() == null) {
            throw new RuntimeException("Attempting to update user not persisted in database.");
        }
        repository.save(user);
    }
}
