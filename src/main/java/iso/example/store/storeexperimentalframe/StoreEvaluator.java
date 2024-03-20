

package iso.example.store.storeexperimentalframe;

import iso.example.store.immutables.*;
import devs.Port;
import devs.msg.Bag;
import devs.msg.time.LongSimTime;
import devs.PDEVSModel;
import java.util.List;


public abstract class StoreEvaluator extends PDEVSModel<LongSimTime, ModifiableStoreEvaluatorState> {

public static String modelIdentifier = "storeEvaluator";
    public static Port<Customer> customerIn = new Port<>("CUSTOMER_IN");


    protected StoreEvaluatorProperties properties;

    public StoreEvaluator(ModifiableStoreEvaluatorState initialState, String identifier, StoreEvaluatorProperties properties) {
        super(initialState, identifier);
        this.properties = properties;
    }

    protected abstract void logCustomer(Customer customer);
    @Override
    protected Bag outputFunction() {
        Bag.Builder bagBuilder = Bag.builder();

        return bagBuilder.build();
    }

}
