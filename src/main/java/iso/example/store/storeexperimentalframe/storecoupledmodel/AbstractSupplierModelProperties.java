

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = SupplierModelProperties.class)
@JsonDeserialize(as = SupplierModelProperties.class)

public abstract class AbstractSupplierModelProperties {

    public abstract Double getMeanTimeToShipHours();
}
