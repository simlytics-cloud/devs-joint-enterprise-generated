

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = AssemblyLine.class)
@JsonDeserialize(as = AssemblyLine.class)

public abstract class AbstractAssemblyLine {

    public abstract List<ProductionRate> getProducts();
}
