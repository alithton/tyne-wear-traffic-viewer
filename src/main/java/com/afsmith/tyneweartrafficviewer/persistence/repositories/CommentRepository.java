package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A data repository, managing access to user comments.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
