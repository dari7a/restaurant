
package dishes.database;

import dishes.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySqlImpl implements Database {

    private Statement statement;
    private String name;
    private String nameHTML;


    private static Statement getStatement() throws SQLException {
        Statement statement = getNewConnection().createStatement();
        return statement;
    }

    public static Connection getNewConnection() {
        String url = "jdbc:mysql://localhost:3306/bookshop?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String password = "Veselova777";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean addDish(Dish dish) {
        String queryFindDish = "SELECT name, nameHTML FROM dishes  WHERE name =\"%s\" ";
        String sql = String.format(queryFindDish, name);
        try {
            statement = getStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String query = "insert into dishes (name, nameHTML)"
                    + " values ('%s','%s')";
            String dishesEntryQuery = String.format(query, dish.getName(), dish.getnameHTML());
            try {
                getStatement().executeUpdate(dishesEntryQuery);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Dish> getDishByName(String name) throws Exception {
        String sql = "SELECT name, nameHTML FROM dishes  WHERE name =\"%s\" ";
        String query = String.format(sql, name);
        ResultSet rs = getStatement().executeQuery(query);
        List<Dish> list = new ArrayList<Dish>();
        while (rs.next()) {
            Dish dish = new Dish();
            dish.setName(rs.getString("name"));
            dish.setnameHTML(rs.getString("nameHTML"));
            list.add(dish);
        }
        return list;
    }

    private void addAllDishes() throws SQLException {
        List<Dish> dishes = new ArrayList<Dish>();
        Dish dish1 = new Dish(name:"Pizza", mameHTML:"file:///C:/Users/Daria/Documents/Pizza.html");
        Dish dish2 = new Dish(name:"Pasta", nameHTML:"file:///C:/Users/Daria/Documents/Pasta.html");
        Dish dish3 = new Dish(name:"Desserts", nameHTML:"file:///C:/Users/Daria/Documents/Desserts.html");
        Dish dish4 = new Dish(name:"Salads", nameHTML:"file:///C:/Users/Daria/Documents/Salads.html");
        Dish dish5 = new Dish(name:"Beverages", nameHTML:"file:///C:/Users/Daria/Documents/Beverages.html");
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        dishes.add(dish4);
        dishes.add(dish5);
        dishes.forEach(dish -> {
            try {
                addDish(dish);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void init() throws SQLException {
        String dishesTableQuery = "CREATE TABLE IF NOT EXISTS DISHES " + "(name TEXT, nameHTML TEXT)";
        getStatement().executeUpdate(dishesTableQuery);

        addAllDishes();

    }
}

