
package dishes;

import dishes.database.Database;
import dishes.database.MySqlImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static Database database;

    public static void main(String[] args) throws Exception {
        database = new MySqlImpl();
        database.init();
        SpringApplication.run(Application.class, args);
    }
}

