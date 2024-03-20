

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ProductRequest.class)
@JsonDeserialize(as = ProductRequest.class)

public abstract class AbstractProductRequest {

    public abstract Product getProduct();
    public abstract Integer getQuantity();
    public abstract Integer getBuiltQuantity();
}
