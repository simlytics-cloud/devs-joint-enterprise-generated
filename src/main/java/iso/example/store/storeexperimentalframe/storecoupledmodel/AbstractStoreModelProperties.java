

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = StoreModelProperties.class)
@JsonDeserialize(as = StoreModelProperties.class)

public abstract class AbstractStoreModelProperties {

    public abstract String getAddress();
    /**
    * A list of ProductDemand for all of the products customers buy in the store.
     */
    public abstract List<ProductDemand> getProductdemand();
    public abstract Integer getOrderInterval();
    public abstract String getStoreName();
    public abstract Integer getOrderQuantity();
}
