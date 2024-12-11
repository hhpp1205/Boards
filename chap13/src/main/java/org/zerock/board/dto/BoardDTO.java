package org.zerock.board.dto;


import lombok.*;

import java.time.LocalDateTime;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long boardId;

    private String title;

    private String content;

    private String writerEmail; //작성자의 이메일(id)

    private String writerName; //작성자의 이름

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private int replyCount; //해당 게시글의 댓글 수


}