
package dishes;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class Controller {
    Buying buying = new Buying(Application.database);

    @RequestMapping(value = "/Order", method = RequestMethod.POST)
    public String Order(@RequestParam String titledish, @RequestParam String fullname, @RequestParam String toEmail, @RequestParam String address, @RequestParam int quantity) throws Exception {
        buying.sendEmail(titledish, fullname, toEmail, address, quantity);
        buying.sendEmail(titledish, fullname, toEmail, address, quantity);
        return "Email already send";
    }

    @RequestMapping(value = "GetDishByName", method = RequestMethod.POST)
    public List<Dish> getDishByName(@RequestParam String name) throws Exception {
        List<Dish> dish = buying.getDishByNameFromDB(name);
        return dish;
    }

    @RequestMapping(value = "/AddDish", method = RequestMethod.POST)
    public String addDish(@RequestParam String name, @RequestParam String nameHTML) throws Exception {
        Dish dish = new Dish(name, nameHTML);
        buying.addDish(dish);
        return "add new dish";
    }

}


