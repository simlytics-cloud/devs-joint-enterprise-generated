

package iso.example.store.storeexperimentalframe.storecoupledmodel;

import devs.InputCouplingHandler;
import devs.msg.PortValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import iso.example.store.immutables.*;


public class StoreCoupledModelInputCouplingHandler extends InputCouplingHandler {

    public StoreCoupledModelInputCouplingHandler() {
      super(Optional.empty());
    }

    @Override
    public void handlePortValue(PortValue<?> portValue, Map<String, List<PortValue<?>>> receiverMap) {

        if (portValue.getPortIdentifier().equals(StoreCoupledModel.customerIntoSystem.getPortIdentifier())) {
            PortValue<Customer> flowPortValue = StoreModel.customerArrival.createPortValue(
                StoreCoupledModel.customerIntoSystem.getValue(portValue));
            addInputPortValue(flowPortValue, determineTargetModel(portValue), receiverMap);
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
