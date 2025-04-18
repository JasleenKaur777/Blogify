package com.example.Blogify.service;

import java.util.List;

import com.example.Blogify.payloads.CommentDTO;

public interface CommentService {
CommentDTO insetComment(CommentDTO dto,Integer postid,Integer userid);
List<CommentDTO> viewAllComments(Integer postid);
List<CommentDTO> getCommentsByUser(Integer userid);
CommentDTO updateComment(Integer commentid,CommentDTO commentdto);
Boolean deleteComment(Integer commentid);

}
