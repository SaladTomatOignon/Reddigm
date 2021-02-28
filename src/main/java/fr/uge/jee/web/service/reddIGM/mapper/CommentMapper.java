package fr.uge.jee.web.service.reddIGM.mapper;

import fr.uge.jee.web.service.reddIGM.dto.CommentDto;
import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.models.VoteType;
import fr.uge.jee.web.service.reddIGM.repositories.VoteCommentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "commentId", expression = "java(comment.getId())")
    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    @Mapping(target = "picture", expression = "java(comment.getUser().getPicture())")
    @Mapping(target = "superCommentId", expression = "java(comment.getSuperComment() != null ? comment.getSuperComment().getId() : null)")
    @Mapping(target = "creationDate", expression = "java(comment.getCreationDate())")
    @Mapping(target = "nbVote", expression = "java(nbVote)")
    @Mapping(target = "myVote", expression = "java(voteType)")
    CommentDto toDto(Comment comment, int nbVote, VoteType voteType);
}