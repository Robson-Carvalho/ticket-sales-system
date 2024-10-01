package main.java.UEFS.system.service;

import main.java.UEFS.system.entity.Comment;
import main.java.UEFS.system.entity.Event;
import main.java.UEFS.system.entity.User;
import main.java.UEFS.system.repository.CommentRepository;

import java.util.List;
import java.util.UUID;

public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final EventService eventService;

    public CommentService() {
        this.commentRepository = new CommentRepository();
        this.userService = new UserService();
        this.eventService = new EventService();
    }

    public Comment getById(UUID id) {return commentRepository.findById(id);}

    public List<Comment> getAll() {return commentRepository.findAll();}

    public Comment create(Comment comment) {
        Event event = eventService.getById(comment.getEventID());
        User user = userService.getById(comment.getUserID());

        if(user == null || event == null) return null;

        if(!event.isActive()){
            commentRepository.save(comment);
            return comment;
        }

        throw new SecurityException("Comentário só pode ser adicionando após a realização do evento.");
    }

    public void update(Comment comment) {commentRepository.save(comment);}

    public void delete(UUID id) {commentRepository.delete(id);}

    public void deleteAll() {commentRepository.deleteAll();}


}

