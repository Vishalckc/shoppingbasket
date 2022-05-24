import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PriceServiceImpl implements PriceService {
    private ConcurrentHashMap<String,Double> map = new ConcurrentHashMap<>();

    public PriceServiceImpl() {
        this.map.put("Apple", 12.0);
        this.map.put("Orange", 32.0);
        this.map.put("Banana", 51.0);
        this.map.put("PineApple", 95.0);
    }

    public Optional<Double> getItemPrice(String itemName) {
        return Optional.ofNullable(map.get(itemName)).isPresent() ?Optional.ofNullable(map.get(itemName)):Optional.of(0.0);
    }
}
