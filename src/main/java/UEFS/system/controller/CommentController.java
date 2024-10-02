package main.java.UEFS.system.controller;

import main.java.UEFS.system.model.Comment;
import main.java.UEFS.system.service.CommentService;

import java.util.List;
import java.util.UUID;

public class CommentController {
    private final CommentService commentService;

    public CommentController() {
        this.commentService = new CommentService();
    }

    public Comment create(UUID userID, UUID eventID, int rating, String content){
        Comment comment = new Comment(userID, eventID, rating, content);
        return commentService.create(comment);
    }

    public Comment getById(UUID id){
        return commentService.getById(id);
    }

    public List<Comment> getAll(){
        return commentService.getAll();
    }

    public void update(UUID id, String content, int rating){
        Comment oldComment = commentService.getById(id);
        oldComment.setContent(content);
        oldComment.setRating(rating);
        commentService.update(oldComment);
    }

    public void delete(UUID id){commentService.delete(id);}

    public void deleteAll(){commentService.deleteAll();}
}
