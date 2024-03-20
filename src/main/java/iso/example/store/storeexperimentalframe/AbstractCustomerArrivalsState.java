

package iso.example.store.storeexperimentalframe;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@Value.Modifiable
@JsonSerialize(as = CustomerArrivalsState.class)
@JsonDeserialize(as = CustomerArrivalsState.class)

public abstract class AbstractCustomerArrivalsState {

    public abstract ProductDemand getProductDemand();
    public abstract List<Customer> getPendingCustomerOutOut();
}
