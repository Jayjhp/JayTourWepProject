package kr.ac.kopo.jaytourboard.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.jaytourboard.dto.BoardDTO;
import kr.ac.kopo.jaytourboard.dto.PageRequestDTO;
import kr.ac.kopo.jaytourboard.security.service.AuthenticationService;
import kr.ac.kopo.jaytourboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/board/", "/Web_report"}) // 2개의 웹을 나누기 위해 사용하였는데, Request 도중 두개 동시에 잡는 현상이 일어나 해결이 필요.
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final AuthenticationService authenticationService; // 로그인 인증 서비스 추가

    @GetMapping("/FirstPage")
    public String firstPage() {
        return "Web_report/FirstPage"; // templates/Web_report/FirstPage.html
    }

    @GetMapping("/Login")
    public String login(HttpSession session) {
        // 이미 로그인된 사용자라면, 로그인 페이지에 접근하지 않고 게시판 목록으로 리디렉션
        if (session.getAttribute("username") != null) {
            return "redirect:/board/list"; // 이미 로그인한 상태이면 게시판 목록 페이지로 리디렉션
        }
        return "Web_report/Login"; // 로그인 페이지
    }

    @PostMapping("/Login")
    public String loginPost(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        boolean loginSuccess = authenticationService.authenticate(username, password);

        if (loginSuccess) {
            return "redirect:/board/list"; // 로그인 후 게시판 목록 페이지로 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다."); // 로그인 실패 시 에러 메시지 전달
            return "redirect:/Web_report/Login"; // 로그인 페이지로 리다이렉트
        }
    }

    @GetMapping("/Join")
    public String join() {
        return "Web_report/Join";
    }

    @GetMapping("/WebPage/1")
    public String showEngPage() {
        return "Web_report/WebPage/1"; // templates/Web_report/WebPage/1.html
    }

    @GetMapping("/WebPage/2")
    public String showJpPage() {
        return "Web_report/WebPage/2"; // templates/Web_report/WebPage/2.html
    }

    @GetMapping("/WebPage/3")
    public String showFinPage() {
        return "Web_report/WebPage/3"; // templates/Web_report/WebPage/3.html
    }

    @GetMapping("/WebPage/4")
    public String showAusPage() {
        return "Web_report/WebPage/4"; // templates/Web_report/WebPage/4.html
    }

    @GetMapping("/WebPage/5")
    public String showFinMsgPage() {
        return "Web_report/WebPage/5"; // templates/Web_report/WebPage/5
    }

    @GetMapping("/WebPage/6")
    public String showAusMsgPage() {
        return "Web_report/WebPage/6"; // templates/Web_report/WebPage/6
    }

    @GetMapping("/WebPage/images")
    public String showTotalImgPage() {
        return "Web_report/WebPage/images"; // templates/Web_report/WebPage/images
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes){
        Long bno = boardService.register(dto);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/read", "modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model){
        BoardDTO boardDTO = boardService.get(bno);
        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        boardService.modify(dto);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes){
        boardService.removeWithReplies(bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

}