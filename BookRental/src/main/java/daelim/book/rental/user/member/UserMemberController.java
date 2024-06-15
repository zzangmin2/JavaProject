package daelim.book.rental.user.member;

import daelim.book.rental.admin.member.AdminMemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/member")
public class UserMemberController {

    @Autowired
    private UserMemberService userMemberService;

    @RequestMapping("/createAccountForm")
    public String createAccountForm(){
        System.out.println("[UserHomeController] createAccountForm()");
        return "/user/member/create_account_form";
    }

    @RequestMapping("/loginForm")
    public String loginForm(){
        System.out.println("[UserHomeController] loginForm()");
        return "/user/member/login_form";
    }

    @RequestMapping(value = "/createAccountConfirm", method = RequestMethod.POST)
    public String createAccountConfirm(UserMemberVo userMemberVo){
        System.out.println("[UserMemberController] createAccountConfirm()");
        String nextPage = "/user/member/create_account_ok";
        int result = userMemberService.createAccount(userMemberVo);
        if(result <= 0){
            nextPage = "/user/member/create_account_ng";
        }
        return nextPage;
    }

//    @PostMapping("/loginConfirm")
//    public String loginConfirm(AdminMemberVo adminMemberVo, @CookieValue(value="loginMember", required = false) String loginMember, HttpServletResponse response) {
//        System.out.println("[AdminMemberController] loginConfirm()");
//        String nextPage = "/admin/member/login_ok";
//        AdminMemberVo loginedAdminMemberVo = adminMemberService.loginConfirm(adminMemberVo);
//        if(loginedAdminMemberVo == null) {
//            nextPage = "/admin/member/login_ng";
//        }else{
//            Cookie cookie = new Cookie("loginMember", loginedAdminMemberVo.getId());
//            cookie.setMaxAge(60 * 30);
//            response.addCookie(cookie);
//        }
//
//        return nextPage;
//
//    }
//
//
//    @GetMapping("/logoutConfirm")
//    public String logoutForm(@CookieValue(value="loginMember", required=false) String loginMember, HttpServletResponse response) {
//        System.out.println("[AdminMemberController.logoutForm]");
//        String nextPage = "redirect:/admin/";
//        Cookie cookie = new Cookie("loginMember", loginMember);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//        return nextPage;
//    }

    //    //세션
    @PostMapping("/loginConfirm")
    public String loginConfirm(UserMemberVo userMemberVo,HttpSession session) {
        System.out.println("[UserMemberController.loginConfirm]");
        String nextPage = "/user/member/login_ok";
        UserMemberVo loginedUserMemberVo = userMemberService.loginConfirm(userMemberVo);

        if(loginedUserMemberVo == null) {
            nextPage = "/user/member/login_ng";
        } else {
            // 세션 생성
            session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
            session.setMaxInactiveInterval(60 * 30);

        }
        return nextPage;
    }

    @GetMapping("/logoutConfirm")
    public String logoutForm(@CookieValue(value="loginMember", required=false) String loginMember, HttpSession session) {
        System.out.println("[UserMemberController.logoutForm]");
        String nextPage = "redirect:/user/";
        session.invalidate();

        return nextPage;
    }

    @GetMapping("/modifyAccountForm")
    public String modifyAccountForm(HttpSession session) {
        System.out.println("[UserMemberController.modifyAccountForm]");
        String nextPage = "/user/member/modify_account_form";
        UserMemberVo userMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
        if(userMemberVo == null) {
            nextPage = "redirect:/user/member/loginForm";
        }
        return nextPage;
    }

    @PostMapping("/modifyAccountConfirm")
    public String modifyAccountConfirm(UserMemberVo userMemberVo, HttpSession session) {
        System.out.println("[UserMemberController] modifyAccountConfirm()");

        String nextPage = "user/member/modify_account_ok";

        int result = userMemberService.modifyAccountConfirm(userMemberVo);

        if (result > 0) {
            UserMemberVo loginedUserMemberVo = userMemberService.getLoginedUserMemberVo(userMemberVo.getNo());

            session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
            session.setMaxInactiveInterval(60 * 30);

        } else {
            nextPage = "user/member/modify_account_ng";

        }

        return nextPage;

    }


    @GetMapping("/findPasswordForm")
    public String findPasswordForm() {
        System.out.println("[UserMemberController] findPasswordForm()");
        String nextPage = "user/member/find_password_form";
        return nextPage;
    }

    @PostMapping("/findPasswordConfirm")
    public String findPasswordConfirm(UserMemberVo userMemberVo){
        System.out.println("[UserMemberController] findPasswordConfirm");
        String nextPage = "user/member/find_password_ok";


        int result = userMemberService.findPasswordConfirm(userMemberVo);

        if(result <= 0 ){
            nextPage = "user/member/find_password_ng";
        }
        return nextPage;
    }
}
