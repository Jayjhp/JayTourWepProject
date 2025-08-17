package kr.ac.kopo.jaytourboard.controller;

import kr.ac.kopo.jaytourboard.dto.ClubMemberDTO;
import kr.ac.kopo.jaytourboard.entity.ClubMember;
import kr.ac.kopo.jaytourboard.service.ClubMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/Join")
@RequiredArgsConstructor
public class ClubMemberJoinController {

    private final ClubMemberService clubMemberService;

    @ModelAttribute("user")
    public ClubMemberDTO clubMemberDTO() {
        return new ClubMemberDTO();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "Web_report/Join";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid ClubMemberDTO clubMemberDTO, BindingResult result) {

        ClubMember existing = clubMemberService.findByEmail(clubMemberDTO.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "Web_report/Join";
        }

        clubMemberService.save(clubMemberDTO);
        return "redirect:/Join?success";
    }

}