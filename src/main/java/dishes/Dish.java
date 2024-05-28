
package dishes;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("dishes")
public class Dish {
    @Indexed(unique = true)
    private String name;
    private String nameHTML;


    public Dish(String name, String nameHTML) {
        this.name = name;
        this.nameHTML = nameHTML;

    }

    public Dish() {

    }

    public String getName() {
        return name;
    }

    public String getnameHTML() {
        return nameHTML;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setnameHTML(String nameHTML) {
        this.nameHTML = nameHTML;
    }


    @Override
    public String toString() {
        return "name = " + " " + name + " nameHTML = " + nameHTML + " ";

    }
}


