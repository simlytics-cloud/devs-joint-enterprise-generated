

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@Value.Modifiable
@JsonSerialize(as = SupplierModelState.class)
@JsonDeserialize(as = SupplierModelState.class)

public abstract class AbstractSupplierModelState {

    public abstract List<Shipment> getPendingSendShipmentOut();
}
