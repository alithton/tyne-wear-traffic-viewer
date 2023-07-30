package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.CommentDTO;
import com.afsmith.tyneweartrafficviewer.entities.Comment;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.exceptions.DataNotFoundException;
import com.afsmith.tyneweartrafficviewer.exceptions.NotAuthenticatedException;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Provides services for handling user comments.
 */

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserService userService;
    private final TrafficDataPersistence persistenceService;

    /**
     * Attempt to save the provided comment.
     * @param codeNumber The code number of the traffic point data entity on which the comment
     *                   was made.
     * @param token The authentication token provided by the user.
     * @param commentDTO The comment data.
     * @throws DataNotFoundException If no traffic data matching the code number could be found.
     * @throws NotAuthenticatedException If the provided token is not valid.
     */
    public void save(String codeNumber, String token, CommentDTO commentDTO) throws DataNotFoundException, NotAuthenticatedException {
        System.out.println("Searching for " + codeNumber + "...");
        TrafficPointData pointData = persistenceService.find(codeNumber);

        System.out.println("Validating user " + commentDTO.getUserName() + "...");
        User user = userService.findByToken(token);

        Comment comment = buildComment(commentDTO, user, pointData);

        pointData.addComment(comment);
        persistenceService.saveComment(pointData, comment);
    }

    // Construct a comment entity.
    private Comment buildComment(CommentDTO dto, User user, TrafficPointData pointData) {
        return Comment.builder()
                      .content(dto.getContent())
                      .created(dto.getCreated())
                      .user(user)
                      .trafficData(pointData)
                      .build();
    }
}
