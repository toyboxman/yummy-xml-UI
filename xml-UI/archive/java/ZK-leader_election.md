***

## leader election in ZK cluster

常常使用一些第三方库的时候，无法配置Logger。但又需要依赖日志来跟踪程序，此时可以通过reflect方式来实现
```java
package example.curator.cluster;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.leader.CancelLeadershipException;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import example.curator.cluster.ClusterEvent.EventType;

public class LeaderElector extends LeaderSelectorListenerAdapter 
        implements Closeable, PathChildrenCacheListener {
    // TODO add log record for this class, starting with '#cluster#'

    private static final String VOTE_PATH = "/vote/leader";
    private final List clusterListeners = Lists.newArrayList();
    private final String name;
    private final LeaderSelector leaderSelector;
    private final PathChildrenCache pathChildrenCache;
    private AtomicBoolean holdLeader = new AtomicBoolean(true);

    public LeaderElector(CuratorFramework client) {
        this(client, "Voter@" + client.getZookeeperClient().getCurrentConnectionString());
    }

    public LeaderElector(CuratorFramework client, String name) {
        this.name = name;

// create a leader selector using the given path for management
// all participants in a given leader selection must use the same path
        leaderSelector = new LeaderSelector(client, VOTE_PATH, this);

// for most cases you will want your instance to requeue when it
// relinquishes leadership
        leaderSelector.autoRequeue();
        leaderSelector.setId(client.getZookeeperClient().getCurrentConnectionString());
        pathChildrenCache = new PathChildrenCache(client, VOTE_PATH, false);
        pathChildrenCache.getListenable().addListener(this);
    }

    public void startVote() throws Exception {
        pathChildrenCache.start();
        leaderSelector.start();
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
// we are now the leader. This method should not return until we want to
// relinquish leadership
        holdLeader.set(true);
        System.out.println(name + " is the leader now." + " from " + name);
        if (!clusterListeners.isEmpty()) {
            ClusterEvent clusterEvent = new ClusterEvent(EventType.LEADER);
            clusterEvent.setLeaderId(leaderSelector.getLeader().getId());
            for (ClusterEventListener listener : clusterListeners) {
                listener.leaderChange(clusterEvent);
            }
        } else {
            System.out.println("#cluster#" + "No listener monitors leader election event");
        }
// hold master role till to die
        while (holdLeader.get()) {
            System.out.println("#cluster#" + name + " still is holding leader role");
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        }
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    public synchronized boolean addEventListener(ClusterEventListener listener) {
        return clusterListeners.add(listener);
    }

    public synchronized boolean removeEventListener(ClusterEventListener listener) {
        return clusterListeners.remove(listener);
    }

    @Override
    public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
        if (!clusterListeners.isEmpty()) {
            ClusterEvent clusterEvent = new ClusterEvent(EventType.MEMBER);
            ImmutableSet.Builder builder = ImmutableSet.builder();
            leaderSelector.getParticipants().stream().forEach(o -> {
                builder.add(o.getId());
            });
            clusterEvent.setParticipants(builder.build());
            for (ClusterEventListener listener : clusterListeners) {
                listener.memberUpdate(clusterEvent);
            }
        } else {
            System.out.println("#cluster#" + "No listener monitors member update event");
        }
    }

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
        System.out.println("#cluster#" + newState + "#" + client.getZookeeperClient().getCurrentConnectionString());
        System.out.println("#cluster#hasLeadership-" + leaderSelector.hasLeadership() + "#"
                + client.getZookeeperClient().getCurrentConnectionString());
        if (client.getConnectionStateErrorPolicy().isErrorState(newState)) {
            ClusterEvent clusterEvent = new ClusterEvent(EventType.MEMBER);
            clusterEvent.setParticipants(ImmutableSet.of());
            for (ClusterEventListener listener : clusterListeners) {
                listener.memberUpdate(clusterEvent);
            }
            if (leaderSelector.hasLeadership()) {
                System.out.println("#cluster#" + name + " relinquish leader role");
                holdLeader.set(false);
                throw new CancelLeadershipException();
            }
        }
    }
}
``` 
