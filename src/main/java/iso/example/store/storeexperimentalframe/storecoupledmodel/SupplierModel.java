

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import devs.Port;
import devs.msg.Bag;
import devs.msg.PortValue;
import devs.msg.time.LongSimTime;
import devs.PDEVSModel;
import java.util.List;
import java.util.ArrayList;

public abstract class SupplierModel extends PDEVSModel<LongSimTime, ModifiableSupplierModelState> {

public static String modelIdentifier = "supplierModel";
    public static Port<Order> receiveOrder = new Port<>("receiveOrder");
    public static Port<Shipment> sendShipment = new Port<>("sendShipment");


    protected SupplierModelProperties properties;

    public SupplierModel(ModifiableSupplierModelState initialState, String identifier, SupplierModelProperties properties) {
        super(initialState, identifier);
        this.properties = properties;
    }


    protected boolean hasPendingOutput() {
        if (!modelState.getPendingSendShipmentOut().isEmpty()) {
            return true;
        }

        return false;
    }

    protected List<PortValue<?>> getPendingOutput() {
        List<PortValue<?>> pendingOutputs = new ArrayList<>();
        for (Shipment shipment : modelState.getPendingSendShipmentOut()) {
            pendingOutputs.add(SupplierModel.sendShipment.createPortValue(shipment));
        }
        modelState.getPendingSendShipmentOut().clear();

        return pendingOutputs;
    }

    @Override
    protected Bag outputFunction() {
        Bag.Builder bagBuilder = Bag.builder();
        bagBuilder.addAllPortValueList(getPendingOutput());
        return bagBuilder.build();
    }

}
