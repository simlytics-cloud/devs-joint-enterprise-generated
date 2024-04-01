

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = Store.class)
@JsonDeserialize(as = Store.class)

public abstract class AbstractStore {

    public abstract String getAddress();
    /**
    * A list of ProductDemand for all of the products customers buy in the store.
     */
    public abstract List<ProductDemand> getProductdemand();
    /**
    * A list of PruductStock elements for all products stocked in the store.
     */
    public abstract List<ProductStock> getProductstock();
    public abstract Integer getOrderInterval();
    public abstract String getStoreName();
    public abstract Integer getOrderQuantity();
}
