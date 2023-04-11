package org.pjj.music.service.impl;

import org.pjj.music.dao.CommentMapper;
import org.pjj.music.domain.Comment;
import org.pjj.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PengJiaJun
 * @Date 2023/04/05 20:06
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insertSelective(comment) > 0 ? true:false;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        return commentMapper.updateCommentMsg(comment) >0 ?true:false;
    }

    //    删除评论
    @Override
    public boolean deleteComment(Integer id) {
        return commentMapper.deleteComment(id) >0 ?true:false;
    }

    @Override
    public List<Comment> allComment() {
        return commentMapper.allComment();
    }

    @Override
    public List<Comment> commentOfSongId(Integer songId) {
        return commentMapper.commentOfSongId(songId);
    }

    @Override
    public List<Comment> commentOfSongListId(Integer songListId) {
        return commentMapper.commentOfSongListId(songListId);
    }
}
