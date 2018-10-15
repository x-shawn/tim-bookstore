package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pojo.Book;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class BookDao {

    @Autowired
    private JdbcTemplate template;

    public List<Book> findAllContaining(String keyword) {
        final String sql = format(
                "SELECT * FROM book WHERE title like %1$s OR author like %1$s OR isbn like %1$s",
                "'%" + keyword + "%'"
        );

        return template.query(sql, (resultSet, rowNum) -> fromDb(resultSet));
    }

    public List<Book> findAllContainingEitherOf(String title, String author, String isbn) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM book WHERE price is not NULL");

        if (hasText(title)) sqlBuilder.append(" AND title like '%").append(title).append("%'");
        if (hasText(author)) sqlBuilder.append(" AND author like '%").append(author).append("%'");
        if (hasText(isbn)) sqlBuilder.append(" AND isbn like '%").append(author).append("%'");

        return template.query(sqlBuilder.toString(), (resultSet, rowNum) -> fromDb(resultSet));
    }

    private Book fromDb(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getString("isbn"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getBigDecimal("price")
        );
    }
}
