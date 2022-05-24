import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class Main {


    public static void main(String[] args) {
        ShoppingBasketService shoppingBasketService=new ShoppingBasketServiceImpl();
        double totalPrice = shoppingBasketService
                .calculateTotalPrice((List.of(
                        "Apple", "Apple", "Orange", "Apple", "PineApple", "Banana", "Orange", "Banana")));
        System.out.println("Total price is " + totalPrice);
    }


}
