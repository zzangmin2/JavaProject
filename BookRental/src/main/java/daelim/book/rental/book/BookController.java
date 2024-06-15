package daelim.book.rental.book;

import daelim.book.rental.admin.BookVO;
import daelim.book.rental.admin.member.AdminMemberVo;
import daelim.book.rental.admin.util.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/book/admin")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UploadFileService uploadFileService;

    @GetMapping("/registerBookForm")
    public String registerBookForm(){
        System.out.println("[BookController] registerBookForm()");
        String nextPage = "admin/book/register_book_form";
        return nextPage;
    }

    @PostMapping("/registerBookConfirm")
    public String registerBookConfirm(BookVO bookVO, MultipartFile file){
        System.out.println("[BookController] registerBookConfirm");
        String nextPage = "admin/book/register_book_ok";

        String savedFileName  = uploadFileService.upload(file);

        if(savedFileName != null){
            bookVO.setThumbnail(savedFileName);
            int result = bookService.registerBook(bookVO);
            if(result <= 0) nextPage = "admin/book/register_book_ng";
        }
        else{
            nextPage = "admin/book/register_book_ng";
        }

        return nextPage;

    }

    @GetMapping("/bookDetail")
    public String bookDetail(Model model, @RequestParam("no") int no){
        System.out.println("bookDetail>>"  + no);

        BookVO bookVO = bookService.selectBook(no);
        model.addAttribute("bookVo", bookVO);

        return "admin/book/book_detail";
    }

    @GetMapping("/getAllBooks")
    public String bookList(Model model){
        System.out.println("bookList>>");
        List<BookVO> bookVOs = bookService.selectAllBook();
        model.addAttribute("bookVOs", bookVOs);
        return "admin/book/full_list_of_books";
    }

    @GetMapping("/searchBook")
    public String searchBook(Model model, @RequestParam("name") String name){

        List<BookVO> bookVOs = bookService.selectSearchBook(name);
        model.addAttribute("bookVos", bookVOs);
        return "book/search_book";
    }


    @GetMapping("/modifyBookForm")
    public String modifyBookForm(Model model, @RequestParam("no") int no, HttpSession session){
        System.out.println("bookUpdate>>");

        AdminMemberVo adminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
        if(adminMemberVo != null) {
            BookVO bookVo = bookService.selectBook(no);
            model.addAttribute("bookVo", bookVo);

            return "admin/book/modify_book_form";
        }else{
                return "redirect:/admin/member/loginForm";
            }

    }

    @PostMapping("/modifyBookConfirm")
    public String modifyBookConfirm(BookVO bookVo,HttpSession session, MultipartFile file){

        AdminMemberVo adminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
        if(adminMemberVo != null) {
            String savedFileName  = uploadFileService.upload(file);

            System.out.println(savedFileName);
            if(savedFileName != null) {
                bookVo.setThumbnail(savedFileName);

                int result = bookService.modifyBookConfirm(bookVo);

                if (result > 0) {
                    System.out.println("됨됨됨됨");
                } else {
                    System.out.println("안됨안됨");
                    return "redirect:/book/admin/getAllBooks";
                }
            }

            return "redirect:/book/admin/getAllBooks";

        }else{
            return "redirect:/admin/member/loginForm";

        }
    }


    @GetMapping("/deleteBookConfirm")
    public String deleteBookConfirm(@RequestParam("no") int no,HttpSession session){
        AdminMemberVo adminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
        if(adminMemberVo != null) {
            bookService.deleteBookConfirm(no);
            return "redirect:/book/admin/getAllBooks";
        }else{
            return "redirect:/admin/member/loginForm";

        }
    }


}
