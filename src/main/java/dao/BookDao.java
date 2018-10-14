package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import pojo.Book;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.springframework.util.StringUtils.hasText;

public class BookDao {

    private JdbcTemplate template;

    public List<Book> findAllContaining(String keyword) {
        checkArgument(hasText(keyword), "Cannot query books with empty keyword");

        // TODO: concatenating SQL ...
        final String sql = "";

        // TODO: O-R mapping ...
        return template.query(sql, (resultSet, rowNum) -> {
            return new Book(null, null, null, null);
        });
    }
}
