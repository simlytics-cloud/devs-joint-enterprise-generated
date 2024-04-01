

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = Customer.class)
@JsonDeserialize(as = Customer.class)

public abstract class AbstractCustomer {

    public abstract String getCustomerId();
    /**
    * The product the customer is looking for
     */
    public abstract Product getProduct();
    /**
    * True if the customer actually purchases the product in the store.
     */
    public abstract Boolean getPurchasedProduct();
}
