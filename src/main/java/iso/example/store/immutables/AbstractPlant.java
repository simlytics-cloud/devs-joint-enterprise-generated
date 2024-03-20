

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = Plant.class)
@JsonDeserialize(as = Plant.class)

public abstract class AbstractPlant {

    public abstract List<AssemblyLine> getAssemblyline();
    public abstract String getAddress();
    public abstract ProductRequest getProductBacklog();
}
