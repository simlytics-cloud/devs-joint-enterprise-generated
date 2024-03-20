
package iso.example.store;

import devs.DevsLoggingActor;
import devs.RootCoordinator;
import devs.msg.DevsMessage;
import devs.msg.InitSim;
import devs.msg.log.DevsLogMessage;
import devs.msg.time.LongSimTime;
import org.apache.pekko.actor.typed.ActorRef;
import org.apache.pekko.actor.typed.Behavior;
import org.apache.pekko.actor.typed.javadsl.*;

public class StoreDevsStreamingSim extends AbstractBehavior<StoreDevsStreamingSim.Command> {

    public interface Command{}
    public static class StoreDevsStreamingSimStart implements Command{}

    @Override
    public Receive<Command> createReceive() {
        ReceiveBuilder<Command> storeDevsStreamingSimReceiveBuilder = newReceiveBuilder();
        storeDevsStreamingSimReceiveBuilder.onMessage(StoreDevsStreamingSimStart.class, this::onStart);
        return storeDevsStreamingSimReceiveBuilder.build();
    }

     private final Behavior<DevsMessage> experimentalFrame;

    private StoreDevsStreamingSim(ActorContext<Command> context, Behavior<DevsMessage> experimentalFrame) {
        super(context);
        this.experimentalFrame = experimentalFrame;
    }

    protected Behavior<Command> onStart(StoreDevsStreamingSimStart start) {
        ActorContext<Command> context = this.getContext();
        ActorRef<DevsLogMessage> loggingActor = context.spawn(DevsLoggingActor.create(System.out), "logger");

        ActorRef<DevsMessage> storeExperimentalFrame = context.spawn(experimentalFrame, "storeExperimentalFrame");

        ActorRef<DevsMessage> rootCoordinator = context.spawn(RootCoordinator.create(
                LongSimTime.builder().t(0L).build(), storeExperimentalFrame
        ), "root");

        rootCoordinator.tell(InitSim.builder().time(LongSimTime.builder().t(0L).build()).build());

        //context.watch(rootCoordinator);
        return Behaviors.same();
    }

    public static Behavior<Command> create(Behavior<DevsMessage> experimentalFrame) {
        return Behaviors.setup(context -> new StoreDevsStreamingSim(context, experimentalFrame));
    }
}

