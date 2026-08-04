package git.david.cuffedplus.config;

public interface ICuffedPlusServerConfigMixin {

    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")
    // (Options: "none", "onlyLock", "onlyUnlock", "both")
    // (Options: "none", "onlyBind", "onlyUnbind", "both")


    /** GENERAL **/
    int increaseReinforcedBlockStrength();
    boolean keepLockedGearOnDeath();


    /** PREFIXES **/
    boolean showRolePrefixes();
    boolean rolePrefixesBold();

    // [1] String prefix [2] Color
    String getPrisonerRolePrefix();
    String getOfficerRolePrefix();
    String getPrisonerRolePrefixColor();
    String getOfficerRolePrefixColor();


    /** PLAYERS GEAR BEHAVIOR **/

    // OPTIONS: "none", "onlyPutOn", "onlyTakeOff", "both"
    String getOtherPlayersJumpsuitBehavior();
    String getOtherPlayersAnkleMonitorBehavior();

    // -- LOCK BEHAVIOR -- //
    // OPTIONS: "none", "onlyLock", "onlyUnlock", "both"
    String getPlayersOwnJumpsuitLockBehavior();
    String getPlayersOwnAnkleMonitorLockBehavior();
    String getOtherPlayersJumpsuitLockBehavior();
    String getOtherPlayersAnkleMonitorLockBehavior();

    String getPlayersOwnTrackerBindingBehavior();
    String getOtherPlayersTrackerBindingBehavior();


    /** PRISONERS GEAR BEHAVIOR **/

    // OPTIONS: "none", "onlyPutOn", "onlyTakeOff", "both"
    String getOtherPrisonersJumpsuitBehavior();
    String getOtherPrisonersAnkleMonitorBehavior();

    // -- LOCK BEHAVIOR -- //
    // OPTIONS: "none", "onlyPutOn", "onlyTakeOff", "both"
    String getPrisonersOwnJumpsuitLockBehavior();
    String getPrisonersOwnAnkleMonitorLockBehavior();
    String getOtherPrisonersJumpsuitLockBehavior();
    String getOtherPrisonersAnkleMonitorLockBehavior();

    String getPrisonersOwnTrackerBindingBehavior();
    String getOtherPrisonersTrackerBindingBehavior();

}
