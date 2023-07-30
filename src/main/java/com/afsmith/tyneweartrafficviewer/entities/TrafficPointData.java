package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class TrafficPointData extends TrafficData {
    private TrafficDataTypes type;
    private String shortDescription;
    @Column(columnDefinition = "TEXT")
    private String longDescription;
    private String locationDescription;
    private Point point;
    private ZonedDateTime creationDate;
    private String dataSourceTypeRef;
    private ZonedDateTime confirmedDate;
    private ZonedDateTime modifiedDate;
    private String severityTypeRefDescription;
    private String lanesAffectedTypeRefDescription;
    private String diversionInForce;
    private String phaseTypeRef;

    @OneToMany(mappedBy = "trafficData")
    private List<Comment> comments;

    // The user who created the item, if the item was user-created; null otherwise.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    public TrafficPointData(String systemCodeNumber,
                            TrafficDataTypes type,
                            String shortDescription,
                            String longDescription,
                            String locationDescription,
                            Point point,
                            ZonedDateTime creationDate,
                            String dataSourceTypeRef,
                            ZonedDateTime confirmedDate,
                            ZonedDateTime modifiedDate,
                            String severityTypeRefDescription,
                            String lanesAffectedTypeRefDescription,
                            String diversionInForce,
                            String phaseTypeRef,
                            List<Comment> comments,
                            User createdBy) {
        super(systemCodeNumber);
        this.type = type;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.locationDescription = locationDescription;
        this.point = point;
        this.creationDate = creationDate;
        this.dataSourceTypeRef = dataSourceTypeRef;
        this.confirmedDate = confirmedDate;
        this.modifiedDate = modifiedDate;
        this.severityTypeRefDescription = severityTypeRefDescription;
        this.lanesAffectedTypeRefDescription = lanesAffectedTypeRefDescription;
        this.diversionInForce = diversionInForce;
        this.phaseTypeRef = phaseTypeRef;
        this.comments = comments;
        this.createdBy = createdBy;
    }

    /**
     * Copy the contents of a TrafficPointData instance into a new instance. This is intended to allow
     * subclasses to be instantiated using an instance of this class.
     * @param pointData The traffic point data to be copied into the new object.
     */
    protected TrafficPointData(TrafficPointData pointData) {
        super(pointData.getSystemCodeNumber());
        type = pointData.getType();
        shortDescription = pointData.getShortDescription();
        longDescription = pointData.getLongDescription();
        locationDescription = pointData.getLocationDescription();
        point = pointData.getPoint();
        creationDate = pointData.getCreationDate();
        dataSourceTypeRef = pointData.getDataSourceTypeRef();
        confirmedDate = pointData.getConfirmedDate();
        modifiedDate = pointData.getModifiedDate();
        severityTypeRefDescription = pointData.getSeverityTypeRefDescription();
        lanesAffectedTypeRefDescription = pointData.getLanesAffectedTypeRefDescription();
        diversionInForce = pointData.getDiversionInForce();
        phaseTypeRef = pointData.getPhaseTypeRef();
        comments = pointData.getComments();
        createdBy = pointData.getCreatedBy();
    }

    /**
     * Add a comment.
     * @param comment The comment to add.
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
