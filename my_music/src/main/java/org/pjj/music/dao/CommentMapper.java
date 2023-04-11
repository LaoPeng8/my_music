package org.pjj.music.dao;

import org.pjj.music.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PengJiaJun
 * @Date 2023/04/05 20:09
 */
@Repository
public interface CommentMapper {

    Comment selectByPrimaryKey(Integer id);

    List<Comment> allComment();

    List<Comment> commentOfSongId(Integer songId);

    List<Comment> commentOfSongListId(Integer songListId);

    int deleteComment(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    int updateCommentMsg(Comment record);

    int updateByPrimaryKey(Comment record);



}
