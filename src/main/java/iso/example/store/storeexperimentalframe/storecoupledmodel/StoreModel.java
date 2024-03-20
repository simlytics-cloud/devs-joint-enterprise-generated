

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import devs.Port;
import devs.msg.Bag;
import devs.msg.time.LongSimTime;
import devs.PDEVSModel;
import java.util.List;


public abstract class StoreModel extends PDEVSModel<LongSimTime, ModifiableStoreModelState> {

public static String modelIdentifier = "storeModel";
    public static Port<Customer> customerArrival = new Port<>("CUSTOMER_ARRIVAL");
    public static Port<Shipment> receiveShipment = new Port<>("RECEIVE_SHIPMENT");
    public static Port<Customer> customerDeparture = new Port<>("CUSTOMER_DEPARTURE");
    public static Port<Order> sendOrder = new Port<>("SEND_ORDER");


    protected StoreModelProperties properties;

    public StoreModel(ModifiableStoreModelState initialState, String identifier, StoreModelProperties properties) {
        super(initialState, identifier);
        this.properties = properties;
    }

    protected abstract void processCustomer(Customer customer);

    protected abstract Order ordering();

    protected abstract void processDelivery(Shipment shipment);
    @Override
    protected Bag outputFunction() {
        Bag.Builder bagBuilder = Bag.builder();
        for (Customer customer : modelState.getPendingCustomerDepartureOut()) {
            bagBuilder.addPortValueList(StoreModel.customerDeparture.createPortValue(customer));
        }
        modelState.getPendingCustomerDepartureOut().clear();

        for (Order order : modelState.getPendingSendOrderOut()) {
            bagBuilder.addPortValueList(StoreModel.sendOrder.createPortValue(order));
        }
        modelState.getPendingSendOrderOut().clear();

        return bagBuilder.build();
    }

}
