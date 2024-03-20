

package iso.example.store.storeexperimentalframe;

import iso.example.store.immutables.*;
import org.apache.pekko.actor.typed.ActorRef;
import org.apache.pekko.actor.typed.Behavior;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import devs.*;
import devs.msg.DevsMessage;
import devs.msg.time.LongSimTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import iso.example.store.storeexperimentalframe.storecoupledmodel.*;


public abstract class AbstractStoreExperimentalFrameFactory {

    protected List<InputCouplingHandler> buildInputCouplings() {
        return Collections.singletonList(new StoreExperimentalFrameInputCouplingHandler());
    }
    protected List<OutputCouplingHandler> buildOutputCouplings() {
        return Collections.singletonList(new StoreExperimentalFrameOutputCouplingHandler());
    }
    protected abstract List<CustomerArrivals> buildCustomerArrivalss();

    protected abstract List<StoreEvaluator> buildStoreEvaluators();

    protected abstract AbstractStoreCoupledModelFactory buildStoreCoupledModelFactory();


    public Map<String, Behavior<DevsMessage>> create(String parentIdentifier) {
        LongSimTime t0 = LongSimTime.builder().t(0L).build();
        Map<String, ActorRef<DevsMessage>> modelSimulators = new HashMap<>();
        PDevsCouplings couplings = new PDevsCouplings(
                    buildInputCouplings(), buildOutputCouplings());
        return Collections.singletonMap(StoreExperimentalFrame.modelIdentifier, Behaviors.setup(context -> {
                buildCustomerArrivalss().stream().forEach(devsModel -> {
                    ActorRef<DevsMessage> atomicModelRef = context.spawn(PDevsSimulator.create(
                        devsModel, t0), devsModel.getModelIdentifier());
                    modelSimulators.put(devsModel.getModelIdentifier(), atomicModelRef);
                });
                buildStoreEvaluators().stream().forEach(devsModel -> {
                    ActorRef<DevsMessage> atomicModelRef = context.spawn(PDevsSimulator.create(
                        devsModel, t0), devsModel.getModelIdentifier());
                    modelSimulators.put(devsModel.getModelIdentifier(), atomicModelRef);
                });
                buildStoreCoupledModelFactory().create(StoreExperimentalFrame.modelIdentifier).entrySet().stream().forEach(entry -> {
                    ActorRef<DevsMessage> coupledModelRef = context.spawn(entry.getValue(), entry.getKey());
                    modelSimulators.put(entry.getKey(), coupledModelRef);
                });
            return new StoreExperimentalFrame(StoreExperimentalFrame.modelIdentifier, parentIdentifier, modelSimulators, couplings, context);
        }));
    }


}
