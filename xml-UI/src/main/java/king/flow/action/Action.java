package king.flow.action;

import java.util.Map;

/**
 *
 * @author LiuJin
 */
public interface Action {
    public void initializeData();
    public void setupListener();
    public void holdComponents(int owner, Map<Integer, Object> components, Map<Integer, Object> components_meta);
}
