package org.zerock.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.entity.Member;
import org.zerock.board.service.MemberService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/members/")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if(error != null){
            model.addAttribute("errorMessage", "Login failed. Please check your email and password.");
        }

        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member, HttpSession session) {
        if (memberService.login(member)) {
            session.setAttribute("member", member);
            return "redirect:/boards/list";
        } else {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", memberService.findAll());
        return "member/list";
    }

    @GetMapping("detail/{email}")
    public String show(@PathVariable String email, Model model) {
        model.addAttribute("member", memberService.findByEmail(email));
        return "member/detail";
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("member", new Member());
        return "member/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Member member) {
        memberService.save(member);
        return "redirect:/members/list";
    }

    @GetMapping("/update/{email}")
    public String update(@PathVariable String email, Model model) {
        model.addAttribute("member", memberService.findByEmail(email));
        return "member/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Member member) {
        memberService.update(member);
        return "redirect:/members/list";
    }

    @GetMapping("/delete/{email}")
    public String delete(@PathVariable String email) {
        memberService.delete(email);
        return "redirect:/members/list";
    }

}
