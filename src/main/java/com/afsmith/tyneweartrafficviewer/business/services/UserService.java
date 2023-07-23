package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.entities.Credentials;
import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.exceptions.UserAlreadyExistsException;
import com.afsmith.tyneweartrafficviewer.persistence.services.UserPersistenceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserPersistenceService userPersistence;
    private final PasswordHashingService passwordHashingService = new PasswordHashingService();

    // The duration of user tokens, in minutes.
    private static final long TOKEN_DURATION = 60L;

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
     * @param token The user token.
     * @return The user that was found or else null.
     */
    public User findByToken(String token) {
        User user = userPersistence.findByToken(token);
        return hasValidToken(user) ? user : null;
    }

    public String save(Credentials credentials) throws UserAlreadyExistsException {
        String salt = passwordHashingService.getSalt();
        String passwordHash = passwordHashingService.hash(credentials.getPassword(), salt);
        User user = new User(credentials.getUsername(), passwordHash, salt);
        setNewToken(user);
        userPersistence.save(user);
        return user.getToken();
    }

    public void update(User user, Credentials credentials) {
        String password = passwordHashingService.hash(credentials.getPassword(), user.getSalt());
        user.setCredentials(new Credentials(credentials.getUsername(), password));
        update(user);
    }

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

    private void setNewToken(User user) {
        user.setToken(passwordHashingService.getToken(20), TOKEN_DURATION);
    }
}
