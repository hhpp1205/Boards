package org.zerock.board.service;


import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import java.util.List;

public interface BoardService {

    Long save(BoardDTO dto, Member member);

    //void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto){

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .boardId(dto.getBoardId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) //int로 처리하도록
                .build();

        return boardDTO;

    }

    List<Board> findAll();

    Board findById(Long boardId);

    Long update(Long boardId, BoardDTO boardDTO);

    void delete(Long boardId);
}
