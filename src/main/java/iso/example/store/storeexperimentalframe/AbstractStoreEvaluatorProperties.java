

package iso.example.store.storeexperimentalframe;

import iso.example.store.immutables.*;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = StoreEvaluatorProperties.class)
@JsonDeserialize(as = StoreEvaluatorProperties.class)

public abstract class AbstractStoreEvaluatorProperties {


}
