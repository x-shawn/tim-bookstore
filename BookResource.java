package restful;

import dao.BookDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Book;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.util.StringUtils.hasText;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/search")
public class BookResource {

    @Autowired
    private BookDao dao;

    @RequestMapping(value = "/simple", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity simpleSearchByKeyword(@RequestParam("keyword") String keyword) {
        if (!hasText(keyword)) return badRequest().body("{ \"message\": \"Quick Search API needs a non-blank keyword !\" }");

        List<Book> found = dao.findAllContaining(keyword);

        return isEmpty(found) ? notFound().build() : ok(found);
    }

    @RequestMapping(value = "/advance", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity advancedSearchBy(
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "isbn", required = false) String isbn) {

        if (!hasText(author) && !hasText(title) && !hasText(isbn)) {
            return badRequest().body("{ \"message\": \"Advanced Search API needs at least 1 non-blank keyword from either: author, title, or isbn !\" }");
        }

        List<Book> found = dao.findAllContainingEitherOf(title, author, isbn);

        return isEmpty(found) ? notFound().build() : ok(found);
    }
}
