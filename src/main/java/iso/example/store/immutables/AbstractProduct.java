

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = Product.class)
@JsonDeserialize(as = Product.class)

public abstract class AbstractProduct {

    /**
    * The name of the product.  Must be unique across the enterprise.
     */
    public abstract String getName();
    /**
    * The cost of one product in dollars.
     */
    public abstract Double getCost();
    /**
    * The weight of one product in pounds.
     */
    public abstract Double getWeight();
}
