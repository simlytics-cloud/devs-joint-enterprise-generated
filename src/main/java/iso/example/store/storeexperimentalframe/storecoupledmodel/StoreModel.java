

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import devs.Port;
import devs.msg.Bag;
import devs.msg.PortValue;
import devs.msg.time.LongSimTime;
import devs.PDEVSModel;
import java.util.List;
import java.util.ArrayList;

public abstract class StoreModel extends PDEVSModel<LongSimTime, ModifiableStoreModelState> {

public static String modelIdentifier = "storeModel";
    public static Port<Customer> customerArrival = new Port<>("customerArrival");
    public static Port<Shipment> receiveShipment = new Port<>("receiveShipment");
    public static Port<Customer> customerDeparture = new Port<>("customerDeparture");
    public static Port<Order> sendOrder = new Port<>("sendOrder");


    protected StoreModelProperties properties;

    public StoreModel(ModifiableStoreModelState initialState, String identifier, StoreModelProperties properties) {
        super(initialState, identifier);
        this.properties = properties;
    }

    protected abstract void processCustomer(Customer customer);

    protected abstract Order ordering();

    protected abstract void processDelivery(Shipment shipment);
    protected boolean hasPendingOutput() {
        if (!modelState.getPendingCustomerDepartureOut().isEmpty()) {
            return true;
        }
        if (!modelState.getPendingSendOrderOut().isEmpty()) {
            return true;
        }

        return false;
    }

    protected List<PortValue<?>> getPendingOutput() {
        List<PortValue<?>> pendingOutputs = new ArrayList<>();
        for (Customer customer : modelState.getPendingCustomerDepartureOut()) {
            pendingOutputs.add(StoreModel.customerDeparture.createPortValue(customer));
        }
        modelState.getPendingCustomerDepartureOut().clear();

        for (Order order : modelState.getPendingSendOrderOut()) {
            pendingOutputs.add(StoreModel.sendOrder.createPortValue(order));
        }
        modelState.getPendingSendOrderOut().clear();

        return pendingOutputs;
    }

    @Override
    protected Bag outputFunction() {
        Bag.Builder bagBuilder = Bag.builder();
        bagBuilder.addAllPortValueList(getPendingOutput());
        return bagBuilder.build();
    }

}
