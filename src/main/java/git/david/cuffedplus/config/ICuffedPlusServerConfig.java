package git.david.cuffedplus.config;

public interface ICuffedPlusServerConfig {

    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")
    // (Options: "none", "onlyLock", "onlyUnlock", "both")
    // (Options: "none", "onlyBind", "onlyUnbind", "both")
    // (Options: "none", onlyPrisoners, "onlyOfficers", "both")

    /** GENERAL **/
    int increaseReinforcedBlockStrength();

    boolean keepLockedGearOnDeath();

    boolean applyRoleDependingOnUniformWorn();

    String getPlayersAttackBehavior(); // (Options: "none", onlyPrisoners, "onlyOfficers", "both")

    boolean canPrisonersAttackWithoutRole();

    String getPrisonersAttackBehavior(); // (Options: "none", onlyPrisoners, "onlyOfficers", "both")

    boolean allowUnlockingTimeLockedRestraints();

    boolean allowBreakingTimeLockedRestraints();

    boolean allowLockpickingTimeLockedRestraints();

    // Messages
    boolean showInfoMessages();

    boolean showSuccessMessages();

    boolean showFailMessages();

    boolean putPlayersInToCreativeWhenAntiGodRestraintTimeLockRunsOut();

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

    // -- LOCK BEHAVIOR -- //

    String getPlayersOwnJumpsuitLockBehavior(); // (Options: "none", "onlyLock", "onlyUnlock", "both")

    String getPlayersOwnAnkleMonitorLockBehavior();

    String getOtherPlayersJumpsuitLockBehavior();

    String getOtherPlayersAnkleMonitorLockBehavior();


    String getPlayersOwnTrackerBindingBehavior();

    String getOtherPlayersTrackerBindingBehavior();

    /** PRISONERS GEAR BEHAVIOR **/

    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")
    String getOtherPrisonersJumpsuitBehavior();

    String getOtherPrisonersAnkleMonitorBehavior();

    // -- LOCK BEHAVIOR -- //
    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")
    String getPrisonersOwnJumpsuitLockBehavior();

    String getPrisonersOwnAnkleMonitorLockBehavior();

    String getOtherPrisonersJumpsuitLockBehavior();

    String getOtherPrisonersAnkleMonitorLockBehavior();

    String getPrisonersOwnTrackerBindingBehavior();

    String getOtherPrisonersTrackerBindingBehavior();
}
