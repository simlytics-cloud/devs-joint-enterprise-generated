

package iso.example.store.storeexperimentalframe;

import iso.example.store.immutables.*;
import devs.Port;
import devs.msg.Bag;
import devs.msg.PortValue;
import devs.msg.time.LongSimTime;
import devs.PDEVSModel;
import java.util.List;
import java.util.ArrayList;

public abstract class CustomerArrivals extends PDEVSModel<LongSimTime, ModifiableCustomerArrivalsState> {

public static String modelIdentifier = "customerArrivals";
    public static Port<Customer> customerOut = new Port<>("customerOut");


    protected CustomerArrivalsProperties properties;

    public CustomerArrivals(ModifiableCustomerArrivalsState initialState, String identifier, CustomerArrivalsProperties properties) {
        super(initialState, identifier);
        this.properties = properties;
    }

    protected abstract Customer generateCustomer();
    protected boolean hasPendingOutput() {
        if (!modelState.getPendingCustomerOutOut().isEmpty()) {
            return true;
        }

        return false;
    }

    protected List<PortValue<?>> getPendingOutput() {
        List<PortValue<?>> pendingOutputs = new ArrayList<>();
        for (Customer customer : modelState.getPendingCustomerOutOut()) {
            pendingOutputs.add(CustomerArrivals.customerOut.createPortValue(customer));
        }
        modelState.getPendingCustomerOutOut().clear();

        return pendingOutputs;
    }

    @Override
    protected Bag outputFunction() {
        Bag.Builder bagBuilder = Bag.builder();
        bagBuilder.addAllPortValueList(getPendingOutput());
        return bagBuilder.build();
    }

}
