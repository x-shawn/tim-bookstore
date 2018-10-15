import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@ComponentScan({ "restful", "dao" })
public class Application {

    public static void main(String[] args) {
        run(Application.class, args);
    }
}
