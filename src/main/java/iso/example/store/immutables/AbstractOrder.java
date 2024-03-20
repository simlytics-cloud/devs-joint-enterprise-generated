

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

    public abstract String getDate();
    public abstract List<ProductRequest> getProductRequest();
    public abstract String getStoreName();
}
