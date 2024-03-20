

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ProductStock.class)
@JsonDeserialize(as = ProductStock.class)

public abstract class AbstractProductStock {

    public abstract Product getProduct();
    public abstract Integer getStockLevel();
}
