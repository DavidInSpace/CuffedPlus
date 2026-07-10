package git.david.cuffedplus.config;

public interface ICuffedPlusServerConfigMixin {

    // TODO: Add config settings to toggle whether prisoners can bind trackers to ankle monitors
    // TODO: Maybe add a config whether prisoners can lock jumpsuits and ankle monitors of others
    // TODO: Make use of all these config settings

    /** Options: "lock" canOnl, "unlock", "both"


    /** PREFIXES **/
    boolean showRolePrefixes();
    boolean rolePrefixesBold();

    // [1] String prefix [2] Color
    String[] getPrisonerRolePrefix();
    String[] getOfficerRolePrefix();



    /** PLAYERS GEAR BEHAVIOR **/

    // OPTIONS: "onlyPutOn", "onlyTakeOff", "both",
    String[] getPlayersJumpsuitBehavior();
    String[] getPlayersAnkleMonitorBehavior();

    // -- LOCK BEHAVIOR -- //
    // OPTIONS: "onlyLock", "onlyUnlock", "both"
    String[] getPlayersJumpsuitLockBehavior();
    String[] getPlayersAnkleMonitorLockBehavior();


    /** PRISONERS GEAR BEHAVIOR **/

    // OPTIONS: "onlyPutOn", "onlyTakeOff", "both",
    String[] getPrisonersJumpsuitBehavior();
    String[] getPrisonersAnkleMonitorBehavior();

    // -- LOCK BEHAVIOR -- //
    // OPTIONS: "onlyLock", "onlyUnlock", "both"
    String[] getPrisonersJumpsuitLockBehavior();
    String[] getPrisonersAnkleMonitorLockBehavior();


    /** BLOCKS **/
    int increaseReinforcedBlockStrength();

}
