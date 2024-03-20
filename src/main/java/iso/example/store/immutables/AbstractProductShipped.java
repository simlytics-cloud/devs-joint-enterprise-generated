

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ProductShipped.class)
@JsonDeserialize(as = ProductShipped.class)

public abstract class AbstractProductShipped {

    public abstract Integer getQuantityShipped();
    public abstract Product getProduct();
}
