package git.david.cuffedplus.items.restraints.base;

public interface ITimedRestraint {

    void tick();
    //default String getTimeString() {return this.hours + " : " + this.minutes + " : " + this.seconds;}
}
