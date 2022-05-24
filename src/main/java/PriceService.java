import java.util.Optional;

public interface PriceService {
    Optional<Double> getItemPrice(String itemName);
}
