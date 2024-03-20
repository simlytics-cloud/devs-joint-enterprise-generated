

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import devs.Port;
import devs.msg.Bag;
import devs.msg.time.LongSimTime;
import devs.PDEVSModel;
import java.util.List;


public abstract class SupplierModel extends PDEVSModel<LongSimTime, ModifiableSupplierModelState> {

public static String modelIdentifier = "supplierModel";
    public static Port<Order> receiveOrder = new Port<>("RECEIVE_ORDER");
    public static Port<Shipment> sendShipment = new Port<>("SEND_SHIPMENT");


    protected SupplierModelProperties properties;

    public SupplierModel(ModifiableSupplierModelState initialState, String identifier, SupplierModelProperties properties) {
        super(initialState, identifier);
        this.properties = properties;
    }


    @Override
    protected Bag outputFunction() {
        Bag.Builder bagBuilder = Bag.builder();
        for (Shipment shipment : modelState.getPendingSendShipmentOut()) {
            bagBuilder.addPortValueList(SupplierModel.sendShipment.createPortValue(shipment));
        }
        modelState.getPendingSendShipmentOut().clear();

        return bagBuilder.build();
    }

}
