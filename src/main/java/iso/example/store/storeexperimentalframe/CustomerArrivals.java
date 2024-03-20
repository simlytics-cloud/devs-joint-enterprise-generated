

package iso.example.store.storeexperimentalframe;

import iso.example.store.immutables.*;
import devs.Port;
import devs.msg.Bag;
import devs.msg.time.LongSimTime;
import devs.PDEVSModel;
import java.util.List;


public abstract class CustomerArrivals extends PDEVSModel<LongSimTime, ModifiableCustomerArrivalsState> {

public static String modelIdentifier = "customerArrivals";
    public static Port<Customer> customerOut = new Port<>("CUSTOMER_OUT");


    protected CustomerArrivalsProperties properties;

    public CustomerArrivals(ModifiableCustomerArrivalsState initialState, String identifier, CustomerArrivalsProperties properties) {
        super(initialState, identifier);
        this.properties = properties;
    }

    protected abstract Customer generateCustomer();
    @Override
    protected Bag outputFunction() {
        Bag.Builder bagBuilder = Bag.builder();
        for (Customer customer : modelState.getPendingCustomerOutOut()) {
            bagBuilder.addPortValueList(CustomerArrivals.customerOut.createPortValue(customer));
        }
        modelState.getPendingCustomerOutOut().clear();

        return bagBuilder.build();
    }

}
