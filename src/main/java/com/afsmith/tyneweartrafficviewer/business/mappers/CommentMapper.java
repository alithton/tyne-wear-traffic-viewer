package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.CommentDTO;
import com.afsmith.tyneweartrafficviewer.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Defines a mapping from comment entities to DTOs.
 */
@Mapper
public interface CommentMapper {

    /**
     * Given a comment entity, get the corresponding DTO.
     * @param comment The comment entity.
     * @return A comment data transfer object.
     */
    @Mapping(source = "comment.user.credentials.username", target = "userName")
    CommentDTO entityToDto(Comment comment);

    /**
     * Given a list of comment entities, get a corresponding list of DTOs.
     * @param comments A list of comments.
     * @return A list of comment data transfer objects.
     */
    List<CommentDTO> entityToDto(List<Comment> comments);
}
