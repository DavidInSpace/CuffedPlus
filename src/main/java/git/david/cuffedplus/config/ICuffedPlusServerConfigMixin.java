package git.david.cuffedplus.config;

public interface ICuffedPlusServerConfigMixin {


    // TODO: Make use of all these config settings

    boolean showRolePrefixes();
    boolean rolePrefixesBold();

    String getPrisonerRolePrefix();
    String getPrisonerRolePrefixColor();

    String getOfficerRolePrefix();
    String getOfficerRolePrefixColor();


    boolean canPrisonersTakeOffAnkleMonitors();
    boolean canPrisonersPutOnAnkleMonitors();
    boolean canPrisonersTakeAnkleMonitorsOffOthers();
    boolean canPrisonersPutAnkleMonitorsOnOthers();

    boolean canPrisonersTakeOffJumpsuits();
    boolean canPrisonersPutOnJumpsuits();
    boolean canPrisonersTakeJumpsuitsOffOthers();
    boolean canPrisonersPutJumpsuitsOnOthers();

}
