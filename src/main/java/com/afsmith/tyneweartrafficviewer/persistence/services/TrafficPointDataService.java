package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.Comment;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.CommentRepository;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.PointDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Provides methods to access any kind of traffic point data.
 */

@RequiredArgsConstructor
@Service
public class TrafficPointDataService {
    private final PointDataRepository<TrafficPointData> repository;
    private final CommentRepository commentRepository;

    /**
     * Find traffic point data using the system code number.
     * @param codeNumber The system code number of the data to be retrieved.
     * @return The found traffic data, or null.
     */
    public TrafficPointData findByCodeNumber(String codeNumber) {
        return repository.findById(codeNumber)
                         .orElse(null);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
