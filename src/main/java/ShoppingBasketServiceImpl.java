import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingBasketServiceImpl implements ShoppingBasketService {
    private PriceService priceService;

    ShoppingBasketServiceImpl() {
        this.priceService = new PriceServiceImpl();
    }

    public double calculateTotalPrice(List<String> itemList) {
        double totalPrice = 0;
        try {
            HashMap<String, Integer> inputMap = new HashMap<>();
            //Convert Input list to hashmap (Fruit vs Qty)
            for (String s : itemList) {
                if (inputMap.containsKey(s))
                    inputMap.put(s, inputMap.get(s) + 1);
                else
                    inputMap.put(s, 1);
            }

            for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
                double price = priceService.getItemPrice(entry.getKey()).get();
                switch (entry.getKey()) {

                    case "Apple": {
                        int qty = inputMap.get(entry.getKey());
                        totalPrice += ((qty / 2) + (qty % 2)) * price;
                        break;
                    }
                    case "Banana": {
                        int qty = inputMap.get(entry.getKey());
                        totalPrice += (((qty / 3) * 2) + (qty % 3)) * price;
                        break;
                    }
                    case "PineApple":
                    case "Orange": {
                        int qty = inputMap.get(entry.getKey());
                        totalPrice += qty * price;
                        break;
                    }
                    //Log Item name whose price is  not found and continue
                    default: {
                        System.out.println("Price not found for " + entry.getKey());
                        totalPrice += 0;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return totalPrice;
    }
}

