package org.pjj.music.service;

import org.pjj.music.domain.Comment;

import java.util.List;

/**
 * @author PengJiaJun
 * @Date 2023/04/05 20:05
 */
public interface CommentService {

    boolean addComment(Comment comment);

    boolean updateCommentMsg(Comment comment);

    boolean deleteComment(Integer id);

    List<Comment> allComment();

    List<Comment> commentOfSongId(Integer songId);

    List<Comment> commentOfSongListId(Integer songListId);

}
