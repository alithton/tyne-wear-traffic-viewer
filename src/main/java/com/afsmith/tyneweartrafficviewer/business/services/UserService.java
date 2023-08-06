package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.entities.Credentials;
import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.exceptions.NotAuthenticatedException;
import com.afsmith.tyneweartrafficviewer.exceptions.UserAlreadyExistsException;
import com.afsmith.tyneweartrafficviewer.persistence.services.UserPersistenceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * Provides services for managing user data and for user authentication.
 */
@Getter
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserPersistenceService userPersistence;
    private final PasswordHashingService passwordHashingService = new PasswordHashingService();

    // The duration of user tokens, in minutes.
    private static final long TOKEN_DURATION = 60L;

    /**
     * Attempt to find a user using the provided credentials. If no users matching
     * the provided username and password combination are found, returns null.
     * @param credentials The user credentials to search for.
     * @return A user matching the credentials, or null.
     */
    public User find(Credentials credentials) {
        User user =  userPersistence.find(credentials.getUsername());
        if (user == null ||
                !passwordIsMatch(credentials.getPassword(), user)) return null;
        setNewToken(user);
        update(user);
        return user;
    }

    /**
     * Find a user with a currently valid token matching the one provided.
     * @param token The authentication token.
     * @return The user that was found.
     * @throws NotAuthenticatedException if the provided token is not valid.
     */
    public User findByToken(String token) throws NotAuthenticatedException {
        User user = userPersistence.findByToken(token);
        if (!hasValidToken(user)) throw new NotAuthenticatedException("Provided authentication token is not valid.");
        return user;
    }

    /**
     * Save the provided user credentials, provided that no user with the provided
     * username already exists. The password is combined with a randomly generated
     * salt and then stored as a hash.
     * @param credentials The credentials to be stored.
     * @return An authentication token.
     * @throws UserAlreadyExistsException If a user with the provided username already exists.
     */
    public String save(Credentials credentials) throws UserAlreadyExistsException {
        String salt = passwordHashingService.getSalt();
        String passwordHash = passwordHashingService.hash(credentials.getPassword(), salt);
        User user = new User(credentials.getUsername(), passwordHash, salt);
        setNewToken(user);
        userPersistence.save(user);
        return user.getToken();
    }

    /**
     * Update the provided user's details with the provided new credentials.
     * @param user The user whose credentials should be updated.
     * @param credentials The new credentials.
     */
    public void update(User user, Credentials credentials) {
        String password = passwordHashingService.hash(credentials.getPassword(), user.getSalt());
        user.setCredentials(new Credentials(credentials.getUsername(), password));
        update(user);
    }

    /**
     * Update the details of the provided user, replacing whatever details were
     * saved previously.
     * @param user The updated user details.
     */
    public void update(User user) {
        userPersistence.update(user);
    }

    // Does the user have a valid token? False if the user is null.
    private boolean hasValidToken(User user) {
        if (user == null) return false;
        ZonedDateTime currentTime = ZonedDateTime.now();
        return currentTime.isBefore(user.getTokenExpiry());
    }

    // Does the provided password match the user's password?
    private boolean passwordIsMatch(String provided, User user) {
        String providedPasswordHash = passwordHashingService.hash(provided, user.getSalt());
        String truePassword = user.getCredentials().getPassword();
        return truePassword.equals(providedPasswordHash);
    }

    // Set a new authentication token.
    private void setNewToken(User user) {
        user.setToken(passwordHashingService.getToken(20), TOKEN_DURATION);
    }
}
