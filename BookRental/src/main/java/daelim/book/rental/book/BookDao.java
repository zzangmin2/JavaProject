package daelim.book.rental.book;

import daelim.book.rental.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class BookDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean isISBN(String isbn){
        System.out.println("[BookDao] isbn:"+ isbn);
        String sql = "SELECT COUNT(*) FROM TB_BOOK WHERE ISBN = ?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, isbn);

        return result > 0? true: false;
    }

    public int insertBook(BookVO bookVO){
        System.out.println("[BookDao] insertBook()");
        String sql = "INSERT INTO TB_BOOK (thumbnail, name, author, publisher, publishYear, isbn, callNumber, rentalAble, regDate, modDate )"
                + "VALUES (?,?,?,?,?,?,?,?, NOW(),NOW())";

        int result = -1;
        try{
            result = jdbcTemplate.update(sql,
                    bookVO.getThumbnail(),
                    bookVO.getName(),
                    bookVO.getAuthor(),
                    bookVO.getPublisher(),
                    bookVO.getPublishYear(),
                    bookVO.getIsbn(),
                    bookVO.getCallNumber(),
                    bookVO.getRentalAble()
                );
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
