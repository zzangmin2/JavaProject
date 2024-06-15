package daelim.book.rental.book;

import daelim.book.rental.admin.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<BookVO> selectAllBook(){
        System.out.println("[BookDao.selectAllBook]");
        String sql = "SELECT * FROM TB_BOOK";

        List<BookVO> bookVOList = new ArrayList<>();

        try{
            bookVOList = jdbcTemplate.query(sql, new RowMapper<BookVO>() {
                @Override
                public BookVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BookVO bookVO = new BookVO();
                    bookVO.setThumbnail(rs.getString("thumbnail"));
                    bookVO.setNo(rs.getInt("no"));
                    bookVO.setName(rs.getString("name"));
                    bookVO.setAuthor(rs.getString("author"));
                    bookVO.setRegDate(rs.getString("regDate"));
                    bookVO.setModDate(rs.getString("modDate"));
                    bookVO.setIsbn(rs.getString("isbn"));
                    bookVO.setPublisher(rs.getString("publisher"));
                    bookVO.setCallNumber(rs.getString("callNumber"));
                    bookVO.setPublishYear(rs.getString("publishYear"));
                    bookVO.setRentalAble(rs.getInt("rentalAble"));
                    return bookVO;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

        return bookVOList;

    }

    public List<BookVO> selectSearchBook(String keyword){
        System.out.println("[BookDao] selectBook()");

        System.out.println(keyword);
        String sql = "SELECT * FROM TB_BOOK WHERE name = ? ";
        List<BookVO> bookVOList = new ArrayList<>();

        try{
            bookVOList = jdbcTemplate.query(sql, new RowMapper<BookVO>() {
                @Override
                public BookVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BookVO bookVO = new BookVO();
                    bookVO.setThumbnail(rs.getString("thumbnail"));
                    bookVO.setNo(rs.getInt("no"));
                    bookVO.setName(rs.getString("name"));
                    bookVO.setAuthor(rs.getString("author"));
                    bookVO.setRegDate(rs.getString("regDate"));
                    bookVO.setModDate(rs.getString("modDate"));
                    bookVO.setIsbn(rs.getString("isbn"));
                    bookVO.setPublisher(rs.getString("publisher"));
                    bookVO.setCallNumber(rs.getString("callNumber"));
                    bookVO.setPublishYear(rs.getString("publishYear"));
                    bookVO.setRentalAble(rs.getInt("rentalAble"));
                    return bookVO;
                }
            }, keyword);
        }catch(Exception e){
            e.printStackTrace();
        }

        return bookVOList;

    }

    public BookVO selectBook(int no){
        System.out.println("[BookDao] selectBook()");

        String sql = "SELECT * FROM TB_BOOK WHERE no = ? ";
        List<BookVO> bookVOList = new ArrayList<>();

        try{
            bookVOList = jdbcTemplate.query(sql, new RowMapper<BookVO>() {
                @Override
                public BookVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BookVO bookVO = new BookVO();
                    bookVO.setThumbnail(rs.getString("thumbnail"));
                    bookVO.setNo(rs.getInt("no"));
                    bookVO.setName(rs.getString("name"));
                    bookVO.setAuthor(rs.getString("author"));
                    bookVO.setRegDate(rs.getString("regDate"));
                    bookVO.setModDate(rs.getString("modDate"));
                    bookVO.setIsbn(rs.getString("isbn"));
                    bookVO.setPublisher(rs.getString("publisher"));
                    bookVO.setCallNumber(rs.getString("callNumber"));
                    bookVO.setPublishYear(rs.getString("publishYear"));
                    bookVO.setRentalAble(rs.getInt("rentalAble"));
                    return bookVO;
                }
            }, no);
        }catch(Exception e){
            e.printStackTrace();
        }

        return bookVOList.size() > 0  ? bookVOList.get(0) : null;

    }

    public int updateBook(BookVO bookVo){
        System.out.println(
                "왜안돼 .." +
                        bookVo.getThumbnail()+" | "+
                bookVo.getName()+" | "+
                bookVo.getAuthor()+" | "+
                bookVo.getPublisher()+" | "+
                bookVo.getPublishYear()+" | "+
                bookVo.getIsbn()+" | "+
                bookVo.getCallNumber()+" | "+
                bookVo.getRentalAble()+" | "+
                bookVo.getNo());

        System.out.println("[BookDao] updateBook()");
        String sql = "UPDATE TB_BOOK SET "
                +"thumbnail = ?, "
                +"name = ?, "
                +"author = ?, "
                +"publisher = ?, "
                +"publishYear = ?, "
                +"isbn = ?, "
                +"callNumber = ?, "
                +"rentalAble = ?, "
                +"modDate = NOW() "
                +"WHERE no = ?";
        int result = -1;
        try{
            result = jdbcTemplate.update(sql,
                    bookVo.getThumbnail(),
                    bookVo.getName(),
                    bookVo.getAuthor(),
                    bookVo.getPublisher(),
                    bookVo.getPublishYear(),
                    bookVo.getIsbn(),
                    bookVo.getCallNumber(),
                    bookVo.getRentalAble(),
                    bookVo.getNo());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int deleteBook(int no) {
        System.out.println("[BookDao] deleteBook()");

        String sql = "DELETE FROM TB_BOOK WHERE no = ?";

        int result = -1;

        try {
            result = jdbcTemplate.update(sql, no);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

