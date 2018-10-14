package restful;

import com.google.common.collect.ImmutableList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Book;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/books")
public class BookResource {

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity quickSearchByKeyword(@RequestParam("keyword") String keyword) {
        List<Book> dummyData = ImmutableList.of(
                new Book("978-0134685991", "Effective Java", "Joshua Bloch", BigDecimal.valueOf(40.83)),
                new Book("978-0596009205", "Head First Java", "Kathy Sierra", BigDecimal.valueOf(36.94))
        );

        return ok(dummyData);
    }
}
