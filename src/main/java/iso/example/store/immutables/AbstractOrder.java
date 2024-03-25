

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = Order.class)
@JsonDeserialize(as = Order.class)

public abstract class AbstractOrder {

    /**
    * The data of the product request formatted as an ISO Format (YYYY-MM-DDThh:mm:ss).
     */
    public abstract String getDate();
    /**
    * A list of ProductRequest (product and quantity) elements for this order.
     */
    public abstract List<ProductRequest> getProductRequest();
    /**
    * The name of the store submitting the order.
     */
    public abstract String getStoreName();
}
