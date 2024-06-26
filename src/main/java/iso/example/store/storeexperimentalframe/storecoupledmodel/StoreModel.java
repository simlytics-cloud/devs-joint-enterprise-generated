

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

    /** This operation is called when a cusotmer enters the store.  The customer is looking for a specific product.  
     * If the product is available in the stockage, it decrements the stockage by one and updates the customer's 
     * purchasedProduct field to true.  If there is no stockage available, this field is set to false.  It adds the
     * customer to the pending output for the CustomerDeparture port.
     * @param customer 
     */
    protected abstract void processCustomer(Customer customer);

    /** This operation represents store ordering.  At a designated interval, store employees check stockages.  If stockage for a product is below a certain threshold, they will order a fixed number from the supplier.
     */
    protected abstract Order ordering();

    /** As a delivery enters the store, the product stock for each product in the delivery is updated accordingly.
     * @param shipment 
     */
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

        for (Order order : modelState.getPendingSendOrderOut()) {
            pendingOutputs.add(StoreModel.sendOrder.createPortValue(order));
        }

        return pendingOutputs;
    }

    protected boolean clearPendingOutput() {
        modelState.getPendingCustomerDepartureOut().clear();
        modelState.getPendingSendOrderOut().clear();

        return false;
    }

    @Override
    protected Bag outputFunction() {
        Bag.Builder bagBuilder = Bag.builder();
        bagBuilder.addAllPortValueList(getPendingOutput());
        return bagBuilder.build();
    }

}
