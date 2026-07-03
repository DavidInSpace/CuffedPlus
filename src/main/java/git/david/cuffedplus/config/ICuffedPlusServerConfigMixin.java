package git.david.cuffedplus.config;

public interface ICuffedPlusServerConfigMixin {

    // TODO: Add config settings to toggle whether prisoners can bind trackers to ankle monitors
    // TODO: Maybe add a config whether prisoners can lock jumpsuits and ankle monitors of others
    // TODO: Make use of all these config settings

    boolean showRolePrefixes();
    boolean rolePrefixesBold();

    String getPrisonerRolePrefix();
    String getPrisonerRolePrefixColor();

    String getOfficerRolePrefix();
    String getOfficerRolePrefixColor();


    boolean canPlayersUnlockOwnJumpsuits();
    boolean canPlayersLockOwnJumpsuits();
    boolean canPlayersUnlockOwnAnkleMonitors();
    boolean canPlayersLockOwnAnkleMonitors();


    boolean canPrisonersTakeAnkleMonitorsOffOthers();
    boolean canPrisonersPutAnkleMonitorsOnOthers();

    boolean canPrisonersTakeJumpsuitsOffOthers();
    boolean canPrisonersPutJumpsuitsOnOthers();

}
