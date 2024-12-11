package org.zerock.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.service.BoardService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/boards/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

   @GetMapping("/list")
   public String list(Model model){
       List<Board> boardList = boardService.findAll();

       if(boardList.isEmpty()){
           model.addAttribute("list", new ArrayList<>());
       }

       model.addAttribute("list", boardList);
       return "board/list";
   }

   @GetMapping("/detail/{boardId}")
   public String detail(@PathVariable("boardId") Long boardId, Model model){
       model.addAttribute("board", boardService.findById(boardId));
       return "board/detail";
   }

    @GetMapping("/save")
    public String save(Model model, HttpSession session){
        Member mebmer = (Member) session.getAttribute("member");
        if (mebmer == null) {
            return "redirect:/members/login";
        }

        model.addAttribute("board", new Board());
       return "board/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        boardService.save(boardDTO, member);
        return "redirect:/boards/list";
    }

    @GetMapping("/update/{boardId}")
    public String update(@PathVariable("boardId") Long boardId, Model model){
       model.addAttribute("board", boardService.findById(boardId));
       return "board/update";
    }

    @PostMapping("/update/{boardId}")
    public String update(@PathVariable("boardId") Long boardId, @ModelAttribute BoardDTO boardDTO) {
        boardService.update(boardId, boardDTO);
        return "redirect:/boards/list";
    }

    @GetMapping("/delete/{boardId}")
    public String delete(@PathVariable("boardId") Long boardId) {
        boardService.delete(boardId);
       return "redirect:/boards/list";
    }



    
}
