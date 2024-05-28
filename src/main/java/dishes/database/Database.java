
package dishes.database;

import dishes.Dish;

import java.util.List;


public interface Database {

    public boolean addDish(Dish dish) throws Exception;

    public List<Dish> getDishByName(String name) throws Exception;

    public void init() throws Exception;

}
