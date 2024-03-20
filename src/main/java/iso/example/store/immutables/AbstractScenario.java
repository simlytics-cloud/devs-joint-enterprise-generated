

package iso.example.store.immutables;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = Scenario.class)
@JsonDeserialize(as = Scenario.class)

public abstract class AbstractScenario {

    public abstract List<ProductDemand> getDemand();
}
