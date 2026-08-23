package git.david.cuffedplus.config;

public interface ICuffedPlusServerConfigMixin {

    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")
    // (Options: "none", "onlyLock", "onlyUnlock", "both")
    // (Options: "none", "onlyBind", "onlyUnbind", "both")


    /** GENERAL **/
    int increaseReinforcedBlockStrength();
    boolean keepLockedGearOnDeath();
    boolean applyRoleDependingOnUniformWorn();
    // (Options: "none", onlyPrisoners, "onlyOfficers", "both")
    String getPlayersAttackBehavior();
    // (Options: "none", onlyPrisoners, "onlyOfficers", "both")
    String getPrisonersAttackBehavior();
    boolean allowUnlockingTimeLockedRestraints();
    boolean allowBreakingTimeLockedRestraints();
    boolean allowLockpickingTimeLockedRestraints();

    /** PREFIXES **/

    boolean showRolePrefixes();
    boolean rolePrefixesBold();

    // [1] String prefix [2] Color
    String getPrisonerRolePrefix();
    String getOfficerRolePrefix();
    String getPrisonerRolePrefixColor();
    String getOfficerRolePrefixColor();


    /** PLAYERS GEAR BEHAVIOR **/

    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")
    String getOtherPlayersJumpsuitBehavior();
    String getOtherPlayersAnkleMonitorBehavior();
    String getOtherPlayersGearInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")

    // -- LOCK BEHAVIOR -- //

    String getPlayersOwnJumpsuitLockBehavior(); // (Options: "none", "onlyLock", "onlyUnlock", "both")
    String getPlayersOwnAnkleMonitorLockBehavior();
    String getPlayersOwnLockInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")
    String getOtherPlayersJumpsuitLockBehavior();
    String getOtherPlayersAnkleMonitorLockBehavior();
    String getOtherPlayersLockInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")

    String getPlayersOwnTrackerBindingBehavior();
    String getPlayersOwnTrackerBindingInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")
    String getOtherPlayersTrackerBindingBehavior();
    String getOtherPlayersTrackerBindingInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")
    /** PRISONERS GEAR BEHAVIOR **/

    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")
    String getOtherPrisonersJumpsuitBehavior();
    String getOtherPrisonersAnkleMonitorBehavior();
    String getOtherPrisonersGearInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")
    // -- LOCK BEHAVIOR -- //
    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")
    String getPrisonersOwnJumpsuitLockBehavior();
    String getPrisonersOwnAnkleMonitorLockBehavior();
    String getPrisonersOwnLockInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")
    String getOtherPrisonersJumpsuitLockBehavior();
    String getOtherPrisonersAnkleMonitorLockBehavior();
    String getOtherPrisonersLockInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")

    String getPrisonersOwnTrackerBindingBehavior();
    String getPrisonersOwnTrackerBindingInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")
    String getOtherPrisonersTrackerBindingBehavior();
    String getOtherPrisonersTrackerBindingInfoMessageBehavior(); // (Options: "none", "onlySuccess", "onlyFail", "both")
}
