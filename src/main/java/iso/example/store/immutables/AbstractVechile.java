

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = Vechile.class)
@JsonDeserialize(as = Vechile.class)

public abstract class AbstractVechile {

    public abstract Double getPayloadCapacity();
    public abstract String getVehicleId();
}
