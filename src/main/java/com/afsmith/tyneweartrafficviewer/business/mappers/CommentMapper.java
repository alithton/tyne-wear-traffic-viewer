package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.CommentDTO;
import com.afsmith.tyneweartrafficviewer.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Mapping(source = "comment.user.credentials.username", target = "userName")
    CommentDTO entityToDto(Comment comment);

    List<CommentDTO> entityToDto(List<Comment> comments);
}
