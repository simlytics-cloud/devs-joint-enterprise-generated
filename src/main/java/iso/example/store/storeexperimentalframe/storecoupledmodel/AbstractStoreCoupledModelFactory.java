

package iso.example.store.storeexperimentalframe.storecoupledmodel;

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



public abstract class AbstractStoreCoupledModelFactory {

    protected List<InputCouplingHandler> buildInputCouplings() {
        return Collections.singletonList(new StoreCoupledModelInputCouplingHandler());
    }
    protected List<OutputCouplingHandler> buildOutputCouplings() {
        return Collections.singletonList(new StoreCoupledModelOutputCouplingHandler());
    }
    protected abstract List<StoreModel> buildStoreModels();

    protected abstract List<SupplierModel> buildSupplierModels();


    public Map<String, Behavior<DevsMessage>> create(String parentIdentifier) {
        LongSimTime t0 = LongSimTime.builder().t(0L).build();
        Map<String, ActorRef<DevsMessage>> modelSimulators = new HashMap<>();
        PDevsCouplings couplings = new PDevsCouplings(
                    buildInputCouplings(), buildOutputCouplings());
        return Collections.singletonMap(StoreCoupledModel.modelIdentifier, Behaviors.setup(context -> {
                buildStoreModels().stream().forEach(devsModel -> {
                    ActorRef<DevsMessage> atomicModelRef = context.spawn(PDevsSimulator.create(
                        devsModel, t0), devsModel.getModelIdentifier());
                    modelSimulators.put(devsModel.getModelIdentifier(), atomicModelRef);
                });
                buildSupplierModels().stream().forEach(devsModel -> {
                    ActorRef<DevsMessage> atomicModelRef = context.spawn(PDevsSimulator.create(
                        devsModel, t0), devsModel.getModelIdentifier());
                    modelSimulators.put(devsModel.getModelIdentifier(), atomicModelRef);
                });

            return new StoreCoupledModel(StoreCoupledModel.modelIdentifier, parentIdentifier, modelSimulators, couplings, context);
        }));
    }


}
