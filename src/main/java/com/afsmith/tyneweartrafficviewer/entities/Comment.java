package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Represents a comment left by a user on a traffic data marker.
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private long id;

    // The user who left the comment.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // The traffic data entity on which the comment was left.
    @ManyToOne
    @JoinColumn(name = "traffic_data_id")
    private TrafficPointData trafficData;

    // When the comment was created.
    private ZonedDateTime created;

    // The text content of the comment.
    @Column(columnDefinition = "TEXT")
    private String content;
}
