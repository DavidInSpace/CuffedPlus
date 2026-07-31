package git.david.cuffedplus.config;

public interface ICuffedPlusServerConfigMixin {

    // TODO: Add config settings to toggle whether prisoners can bind trackers to ankle monitors
    // TODO: Maybe add a config whether prisoners can lock jumpsuits and ankle monitors of others
    // TODO: Make use of all these config settings

    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")
    // (Options: "none", "onlyPutOn", "onlyTakeOff", "both")


    /** PREFIXES **/
    boolean showRolePrefixes();
    boolean rolePrefixesBold();

    // [1] String prefix [2] Color
    String getPrisonerRolePrefix();
    String getOfficerRolePrefix();



    /** PLAYERS GEAR BEHAVIOR **/

    // OPTIONS: "none", "onlyPutOn", "onlyTakeOff", "both"
    String getOtherPlayersJumpsuitBehavior();
    String getOtherPlayersAnkleMonitorBehavior();

    // -- LOCK BEHAVIOR -- //
    // OPTIONS: "onlyLock", "onlyUnlock", "both"
    String getPlayersOwnJumpsuitLockBehavior();
    String getPlayersOwnAnkleMonitorLockBehavior();
    String getOtherPlayersJumpsuitLockBehavior();
    String getOtherPlayersAnkleMonitorLockBehavior();

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


    /** BLOCKS **/
    int increaseReinforcedBlockStrength();

}
