package daelim.book.rental.book;

import daelim.book.rental.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
