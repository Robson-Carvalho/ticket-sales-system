package main.java.UEFS.system.testFacade.CommentTestFacade;

import main.java.UEFS.system.controller.CommentController;
import main.java.UEFS.system.controller.UserController;
import main.java.UEFS.system.model.Comment;
import main.java.UEFS.system.model.User;

import java.util.UUID;

public class CommentTestFacade {
    private final CommentController commentController;

    public CommentTestFacade() {
        commentController = new CommentController();
    }

    public String create(String userID, String eventID, int rating, String content){
        Comment comment = commentController.create(UUID.fromString(userID), UUID.fromString(eventID), rating, content);
        return comment.getId().toString();
    }

    public Comment getById(String id){
        return commentController.getById(UUID.fromString(id));
    }

    public String getContentById(String id){
        Comment comment = commentController.getById(UUID.fromString(id));
        return comment.getContent();
    }

    public int getRatingCommentById(String id){
        Comment comment = commentController.getById(UUID.fromString(id));
        return comment.getRating();
    }

    public String getUserIdById(String id){
        Comment comment = commentController.getById(UUID.fromString(id));
        return comment.getUserID().toString();
    }

    public String getEventIdById(String id){
        Comment comment = commentController.getById(UUID.fromString(id));
        return comment.getEventID().toString();
    }

    public float getEventRatingByEventId(String id){
        return commentController.getEventRatingByEventId(UUID.fromString(id));
    }

    public void delete(String commentId){
        commentController.delete(UUID.fromString(commentId));
    }

    public void deleteAllComments(){
        commentController.deleteAll();
    }
}
