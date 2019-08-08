package life.cwh.community.mapper;

import life.cwh.community.model.Comment;

public interface CommentEXTMapper {
    int incCommentCount(Comment comment);
}