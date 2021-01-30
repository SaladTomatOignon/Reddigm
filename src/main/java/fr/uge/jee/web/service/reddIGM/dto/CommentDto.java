package fr.uge.jee.web.service.reddIGM.dto;

import java.time.Instant;

public class CommentDto {
    private String text;
    private Instant creationDate;
    private Long postId;
    private String userName;

    public CommentDto() {
    }

    public CommentDto(String text, Instant creationDate, Long postId, String userName) {
        this.text = text;
        this.creationDate = creationDate;
        this.postId = postId;
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
