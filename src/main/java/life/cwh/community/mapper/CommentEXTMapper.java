package life.cwh.community.mapper;

import life.cwh.community.model.Comment;
import life.cwh.community.model.CommentExample;
import life.cwh.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentEXTMapper {
    int incCommentCount(Comment comment);
}