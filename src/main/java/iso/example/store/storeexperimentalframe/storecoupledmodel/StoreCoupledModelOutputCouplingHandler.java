

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import iso.example.store.immutables.*;
import devs.OutputCouplingHandler;
import devs.msg.PortValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import iso.example.store.immutables.*;


public class StoreCoupledModelOutputCouplingHandler extends OutputCouplingHandler {

    public StoreCoupledModelOutputCouplingHandler() {
        super(Optional.empty(), Optional.empty(), Optional.empty());
    }

    @Override
    public void handlePortValue(String sender, PortValue<?> portValue,
                                Map<String, List<PortValue<?>>> receiverMap,
                                List<PortValue<?>> outputMessages) {

        if (portValue.getPortIdentifier().equals(SupplierModel.sendShipment.getPortIdentifier())) {
            PortValue<Shipment> flowPortValue = StoreModel.receiveShipment.createPortValue(
                SupplierModel.sendShipment.getValue(portValue));
            addInputPortValue(flowPortValue, determineTargetModel(portValue), receiverMap);
        }

        if (portValue.getPortIdentifier().equals(StoreModel.sendOrder.getPortIdentifier())) {
            PortValue<Order> flowPortValue = SupplierModel.receiveOrder.createPortValue(
                StoreModel.sendOrder.getValue(portValue));
            addInputPortValue(flowPortValue, determineTargetModel(portValue), receiverMap);
        }

        if (portValue.getPortIdentifier().equals(StoreModel.customerDeparture.getPortIdentifier())) {
            PortValue<Customer> flowPortValue = StoreCoupledModel.customerOutSystem.createPortValue(
                StoreModel.customerDeparture.getValue(portValue));
            outputMessages.add(flowPortValue);
        }

    }

    protected String determineTargetModel(PortValue<?> fromPortValue) {
        return switch (fromPortValue.getPortIdentifier()) {
            case "customerArrival" -> StoreModel.modelIdentifier;
            case "receiveShipment" -> StoreModel.modelIdentifier;
            case "receiveOrder" -> SupplierModel.modelIdentifier;
            case "customerOutSystem" -> StoreCoupledModel.modelIdentifier;
            default -> throw new IllegalArgumentException(
                    "Could not identify target model from PortValue with identifier " +
                            fromPortValue.getPortIdentifier());
        };
    }

}
