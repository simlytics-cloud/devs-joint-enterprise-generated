

package iso.example.store.storeexperimentalframe;

import devs.InputCouplingHandler;
import devs.msg.PortValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import iso.example.store.storeexperimentalframe.storecoupledmodel.StoreCoupledModel;
import iso.example.store.immutables.*;


public class StoreExperimentalFrameInputCouplingHandler extends InputCouplingHandler {

    public StoreExperimentalFrameInputCouplingHandler() {
      super(Optional.empty());
    }

    @Override
    public void handlePortValue(PortValue<?> portValue, Map<String, List<PortValue<?>>> receiverMap) {


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
