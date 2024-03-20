

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

    public abstract String getName();
    public abstract Double getCost();
    public abstract Double getWeight();
}
