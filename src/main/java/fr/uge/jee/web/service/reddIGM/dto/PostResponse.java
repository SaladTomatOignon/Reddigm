package fr.uge.jee.web.service.reddIGM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String postName;
    private Long userId;
    private String url;
    private String description;
    private Long subjectId;
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
    private boolean upVote;
    private boolean downVote;
}