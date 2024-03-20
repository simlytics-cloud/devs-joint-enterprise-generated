

package iso.example.store.storeexperimentalframe;

import iso.example.store.immutables.*;
import devs.OutputCouplingHandler;
import devs.msg.PortValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import iso.example.store.storeexperimentalframe.storecoupledmodel.StoreCoupledModel;
import iso.example.store.immutables.*;


public class StoreExperimentalFrameOutputCouplingHandler extends OutputCouplingHandler {

    public StoreExperimentalFrameOutputCouplingHandler() {
        super(Optional.empty(), Optional.empty(), Optional.empty());
    }

    @Override
    public void handlePortValue(String sender, PortValue<?> portValue,
                                Map<String, List<PortValue<?>>> receiverMap,
                                List<PortValue<?>> outputMessages) {

        if (portValue.getPortIdentifier().equals(CustomerArrivals.customerOut.getPortIdentifier())) {
            PortValue<Customer> flowPortValue = StoreCoupledModel.customerIntoSystem.createPortValue(
                CustomerArrivals.customerOut.getValue(portValue));
            addInputPortValue(flowPortValue, determineTargetModel(portValue), receiverMap);
        }

        if (portValue.getPortIdentifier().equals(StoreCoupledModel.customerOutSystem.getPortIdentifier())) {
            PortValue<Customer> flowPortValue = StoreEvaluator.customerIn.createPortValue(
                StoreCoupledModel.customerOutSystem.getValue(portValue));
            addInputPortValue(flowPortValue, determineTargetModel(portValue), receiverMap);
        }

    }

    protected String determineTargetModel(PortValue<?> fromPortValue) {
        return switch (fromPortValue.getPortIdentifier()) {
            case "customerIntoSystem" -> StoreCoupledModel.modelIdentifier;
            case "customerIn" -> StoreEvaluator.modelIdentifier;
            default -> throw new IllegalArgumentException(
                    "Could not identify target model from PortValue with identifier " +
                            fromPortValue.getPortIdentifier());
        };
    }

}
