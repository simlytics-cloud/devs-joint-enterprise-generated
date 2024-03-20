

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@Value.Modifiable
@JsonSerialize(as = StoreModelState.class)
@JsonDeserialize(as = StoreModelState.class)

public abstract class AbstractStoreModelState {

    public abstract Integer getLastOrderTime();
    public abstract List<ProductStock> getProductstock();
    public abstract List<Order> getPendingSendOrderOut();
    public abstract List<Customer> getPendingCustomerDepartureOut();
}
