package kr.ac.kopo.jaytourboard.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import kr.ac.kopo.jaytourboard.constraint.FieldMatch;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordConfirm", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})

@Getter
@Setter
public class ClubMemberDTO {
    private String email;
    private String password;
    private String name;

    private String confirmEmail;

    // 회원가입 화면에서 사용자가 비밀번호를 다시 입력하도록 요구할 경우 추가 가능
    private String passwordConfirm;

    private boolean fromSocial;
}
