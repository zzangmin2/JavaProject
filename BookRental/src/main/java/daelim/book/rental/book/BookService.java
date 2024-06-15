package daelim.book.rental.book;

import daelim.book.rental.admin.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;
    final static public int BOOK_ISBN_ALREADY_EXISTS = 0; // 이미 등록된 도서
    final static public int BOOK_REGISTER_SUCCESS = 1; // 신규 도서 등록 성공
    final static public int BOOK_REGISTER_FAIL = -1; // 신규 도서 등록 실패

    public int registerBook(BookVO bookVO){
        System.out.println("[BookService] registerBook()");
        boolean isISBN = bookDao.isISBN(bookVO.getIsbn());
        if(!isISBN) {
            int result = bookDao.insertBook(bookVO);
            if(result > 0 ) return BOOK_REGISTER_SUCCESS;
            else return BOOK_REGISTER_FAIL;
        } else {
            return BOOK_ISBN_ALREADY_EXISTS;
        }

    }

    public List<BookVO> selectAllBook(){
        System.out.println("[BookService] getAllBooks");
        return bookDao.selectAllBook();
    }

    public List<BookVO> selectSearchBook(String keyword){
        System.out.println("[BookService] getSearchBooks");
        return bookDao.selectSearchBook(keyword);
    }

    public BookVO selectBook(int no){
        System.out.println("[BookService] getBook");
        return bookDao.selectBook(no);
    }

    public int modifyBookConfirm(BookVO bookVo){
        System.out.println("[BookService] updateBook");
        return bookDao.updateBook(bookVo);
    }

    public int deleteBookConfirm(int no){
        System.out.println("[BookService] updateBook");
        return bookDao.deleteBook(no);
    }

}
