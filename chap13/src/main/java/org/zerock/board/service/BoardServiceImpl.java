package org.zerock.board.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;


    @Override
    public Long save(BoardDTO dto, Member member) {

        log.info(dto);

        Board board  = dtoToEntity(dto);
        board.setWriter(member);

        repository.save(board);

        return board.getBoardId();
    }

    @Override
    public List<Board> findAll() {
        return repository.findAll();
    }

    @Override
    public Board findById(Long boardId) {
        return repository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Long update(Long boardId, BoardDTO boardDTO) {
        Board originBoard = repository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException());

        Board board = dtoToEntity(boardDTO);

        originBoard.update(board);

        return repository.save(originBoard).getBoardId();
    }

    @Override
    public void delete(Long boardId) {
        repository.deleteById(boardId);
    }


};
