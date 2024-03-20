

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = Shipment.class)
@JsonDeserialize(as = Shipment.class)

public abstract class AbstractShipment {

    public abstract Plant getPlant();
    public abstract Vechile getVechile();
    public abstract List<Store> getStore();
    public abstract List<ProductShipped> getProductshipped();
}
