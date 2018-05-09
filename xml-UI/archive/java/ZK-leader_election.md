***

## leader election in ZK cluster
[zk_explain](https://zookeeper.apache.org/doc/current/recipes.html#sc_leaderElection)
---
Curator框架提供了一套基于ZK的选举接口，可以很方便地完成在cluster中选中一个节点作为leader，
来履行统一信息发布的功能
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

定义cluster事件监听通知的接口
```java
public interface ClusterEventListener {
    default public void memberUpdate(ClusterEvent event) {
    }

    default public void leaderChange(ClusterEvent event) {
    }
}
```

定义cluster事件类型
```java
public class ClusterEvent {
    public static enum EventType {
        LEADER, MEMBER;
    }

    private final EventType type;

    private String leaderId = null;
    private Set<String> participants = null;

    public ClusterEvent(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setParticipants(Set<String> participants) {
        this.participants = participants;
    }

    public Set<String> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("{Event type: [").append(type.name()).append("], ");
        switch (type) {
        case LEADER:
            sb.append("Leader ID: [").append(leaderId).append(']');
            break;
        case MEMBER:
            sb.append("Member list: [").append(String.join(", ", participants)).append(']');
            break;
        default:
            break;
        }
        return sb.append('}').toString();
    }
}
```

定义cluster service提供选举服务
```java
public class ZKClusterService implements ClusterEventListener, NodeCacheListener {
    private static final String PORT = "2888";

    private static final String CLUSTER_SHARDING = "/sharding";

    private static final Logger logger = LoggerFactory.getLogger(ZKClusterService.class);

    private static ZKClusterService instance;
    private static UUID myUuid;
    private static CuratorFramework cf;

    private Set<String> memberList = Sets.newHashSet();

    private static NodeCache nodeCache;
    private static LeaderElector voter;

    private ZKClusterService() {
    }

    public static ZKClusterService getInstance() {
        return instance;
    }

    /**
     * Initialize a zk cluster service management. before get a cluster service,
     * you need to call init firstly.
     *
     * @param cf
     *            curator framework instance, which handle client connection to
     *            zookeeper server
     * @param uuid
     *            current node UUID
     * @throws Exception
     */
    public static void init(final CuratorFramework curatorFramework, final UUID uuid)
            throws Exception {
        instance = new ZKClusterService();
        cf = curatorFramework;
        myUuid = uuid;
        instance.joinCluster();
    }

    private void joinCluster() throws Exception {
        cf.getConnectionStateListenable().addListener((client, state) -> {
            if (state.equals(ConnectionState.CONNECTED)) {
                Executors.newSingleThreadScheduledExecutor().execute(() -> {
                    try {
                        ...
                        if (cf.checkExists().forPath(CLUSTER_SHARDING) == null) {
                            cf.create().forPath(CLUSTER_SHARDING, "".getBytes());
                        }

                        if (cf.checkExists().forPath(VOTE_PATH) == null) {
                            cf.create().forPath(VOTE_PATH, "".getBytes());
                        }
                        voter = new LeaderElector(cf);
                        voter.addEventListener(instance);
                        nodeCache = new NodeCache(cf, CLUSTER_SHARDING);
                        nodeCache.getListenable().addListener(instance);
                        voter.startVote();
                        nodeCache.start();
                        logger.info("#cluster#[{}] succeeds in joining cluster", voter.getName());
                    } catch (Exception e) {
                        logger.warn("#cluster#Fail to join cluster due to : ", e);
                    }
                });
            } else {
                logger.info("#cluster#local zk [{}] connection state becomes to [{}]",
                        cf.getZookeeperClient().getCurrentConnectionString(), state);
            }
        });
        cf.start();
    }

    @Override
    public void memberUpdate(ClusterEvent event) {
        // Create a new member map
        logger.info("#cluster#Members changed to {}", event);
        ...
    }

    @Override
    public void leaderChange(ClusterEvent event) {
        logger.info("#cluster#Leader changed to {}", event);
        ...
    }

    @Override
    public void nodeChanged() throws Exception {
    ...
    }
}

```
