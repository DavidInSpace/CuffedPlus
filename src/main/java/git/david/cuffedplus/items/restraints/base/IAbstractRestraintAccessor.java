package git.david.cuffedplus.items.restraints.base;

public interface IAbstractRestraintAccessor {

    // Getters
    boolean isTimeLocked();

    // Setters
    void setTimeLocked(boolean time_locked);

    long getTicksTime();

    void setTicksTime(long amount);

    long getTicks();

    void setTicks(long amount);

    int getPlayerTickCount();

    void setPlayerTickCount(int amount);

    boolean getDropTimeLock();

    void setDropTimeLock(boolean drop);
}
