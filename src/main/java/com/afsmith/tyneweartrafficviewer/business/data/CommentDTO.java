package com.afsmith.tyneweartrafficviewer.business.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

/**
 * A representation of user comments that contains the information necessary to
 * display them on the frontend.
 */

@Getter
@AllArgsConstructor
public class CommentDTO {

    // An identifier for the comment.
    private long id;

    // Name of the user who left the comment.
    private String userName;

    // When the comment was created.
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime created;

    // The comment text.
    private String content;
}
