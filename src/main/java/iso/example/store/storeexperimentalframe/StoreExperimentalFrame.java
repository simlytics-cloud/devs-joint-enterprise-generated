

package iso.example.store.storeexperimentalframe;

import iso.example.store.immutables.*;
import org.apache.pekko.actor.typed.ActorRef;
import org.apache.pekko.actor.typed.javadsl.ActorContext;
import devs.PDevsCoordinator;
import devs.PDevsCouplings;
import devs.Port;
import devs.msg.DevsMessage;
import devs.msg.time.LongSimTime;
import java.util.Map;
import iso.example.store.storeexperimentalframe.storecoupledmodel.*;

public class StoreExperimentalFrame extends PDevsCoordinator<LongSimTime> {

    public static String modelIdentifier = "storeExperimentalFrame";



    public StoreExperimentalFrame(
            String modelIdentifier,
            String parentId, Map<String, ActorRef<DevsMessage>> modelsSimulators,
            PDevsCouplings couplings,
            ActorContext<DevsMessage> context) {
        super(modelIdentifier, parentId, modelsSimulators, couplings, context);
    }
}
