

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import org.apache.pekko.actor.typed.ActorRef;
import org.apache.pekko.actor.typed.javadsl.ActorContext;
import devs.PDevsCoordinator;
import devs.PDevsCouplings;
import devs.Port;
import devs.msg.DevsMessage;
import devs.msg.time.LongSimTime;
import java.util.Map;


public class StoreCoupledModel extends PDevsCoordinator<LongSimTime> {

    public static String modelIdentifier = "storeCoupledModel";

    public static Port<Customer> customerIntoSystem = new Port<>("CUSTOMER_INTO_SYSTEM");
    public static Port<Customer> customerOutSystem = new Port<>("CUSTOMER_OUT_SYSTEM");

    public StoreCoupledModel(
            String modelIdentifier,
            String parentId, Map<String, ActorRef<DevsMessage>> modelsSimulators,
            PDevsCouplings couplings,
            ActorContext<DevsMessage> context) {
        super(modelIdentifier, parentId, modelsSimulators, couplings, context);
    }
}
