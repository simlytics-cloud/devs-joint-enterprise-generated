

package iso.example.store.storeexperimentalframe;

import iso.example.store.immutables.*;
import devs.Port;
import devs.msg.Bag;
import devs.msg.PortValue;
import devs.msg.time.LongSimTime;
import devs.PDEVSModel;
import java.util.List;
import java.util.ArrayList;

public abstract class StoreEvaluator extends PDEVSModel<LongSimTime, ModifiableStoreEvaluatorState> {

public static String modelIdentifier = "storeEvaluator";
    public static Port<Customer> customerIn = new Port<>("customerIn");


    protected StoreEvaluatorProperties properties;

    public StoreEvaluator(ModifiableStoreEvaluatorState initialState, String identifier, StoreEvaluatorProperties properties) {
        super(initialState, identifier);
        this.properties = properties;
    }

    protected abstract void logCustomer(Customer customer);
    protected boolean hasPendingOutput() {

        return false;
    }

    protected List<PortValue<?>> getPendingOutput() {
        List<PortValue<?>> pendingOutputs = new ArrayList<>();

        return pendingOutputs;
    }

    @Override
    protected Bag outputFunction() {
        Bag.Builder bagBuilder = Bag.builder();
        bagBuilder.addAllPortValueList(getPendingOutput());
        return bagBuilder.build();
    }

}
