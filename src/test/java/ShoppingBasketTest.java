import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ShoppingBasketTest {
    private ShoppingBasketService shoppingBasketService;
    private Utility utility;
    private PriceService priceService;

    @Before
    public void setup() {
        this.shoppingBasketService = new ShoppingBasketServiceImpl();
        this.utility=new Utility();
        this.priceService=new PriceServiceImpl();
    }

    @Test
    public void testShoppingListWhenAllItemsHavePriceMatch() {
        List<String> fruits = List.of("Apple", "Apple", "Orange", "Apple", "PineApple", "Banana", "Orange", "Banana");
        double totalPrice = shoppingBasketService.calculateTotalPrice(fruits);
        assertEquals(285.0, totalPrice, 0);
    }

    @Test
    public void testShoppingListWhenAnItemDoesNotHaveAPriceMapping() {
        double totalPrice = shoppingBasketService.calculateTotalPrice(
                List.of("Apple", "Apple", "Orange", "Apple", "PineApple", "Banana", "Orange", "Banana", "NoFruit"));
        assertEquals(285.0, totalPrice, 0);

    }

    @Test
    public void testPriceOf3ApplesPurchasedWhenDiscount2For1() {
        double totalPrice = shoppingBasketService.calculateTotalPrice(
                List.of("Apple", "Apple", "Apple"));
        assertEquals(12.0,priceService.getItemPrice("Apple").get(),0);
        assertEquals(24.0, totalPrice, 0);
    }

    @Test
    public void testPriceOf2ApplesPurchasedWhenDiscount2For1() {
        double totalPrice = shoppingBasketService.calculateTotalPrice(
                List.of("Apple", "Apple"));
        assertEquals(12.0,priceService.getItemPrice("Apple").get(),0);
        assertEquals(12.0, totalPrice, 0);
    }

    @Test
    public void testPriceOf2BananaPurchasedWhenDiscount3For2() {
        double totalPrice = shoppingBasketService.calculateTotalPrice(
                List.of("Banana", "Banana"));
        assertEquals(51.0,priceService.getItemPrice("Banana").get(),0);
        assertEquals(102.0, totalPrice, 0);
    }

    @Test
    public void testPriceOf3BananaPurchasedWhenDiscount3For2() {
        double totalPrice = shoppingBasketService.calculateTotalPrice(
                List.of("Banana", "Banana", "Banana"));
        assertEquals(51.0,priceService.getItemPrice("Banana").get(),0);
        assertEquals(102.0, totalPrice, 0);
    }

    @Test
    public void testShoppingListWithConcurrency() throws InterruptedException {
        int numberOfThreads = 10;
        double totalPrice;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                double price = shoppingBasketService.calculateTotalPrice(
                        utility.getRandomList());
                //System.out.println(Thread.currentThread().getName()+" total price: "+price);
                latch.countDown();
            });
        }
        latch.await();
        assertEquals(latch.getCount(), 0);

    }

}
