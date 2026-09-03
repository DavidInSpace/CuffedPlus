package git.david.cuffedplus.config.base;

import git.david.cuffedplus.constants.Styles;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.util.ArrayList;

public record ConfigDescriptions() {
    public static ArrayList<DescriptionHolder> DESCRIPTIONS = new ArrayList<>();

    public static void initDescriptions() {
        DESCRIPTIONS.add(new DescriptionHolder("KEEP_LOCKED_GEAR_ON_DEATH",
                getTrueBooleanTooltip("When dying, gear (jumpsuits and ankle monitors) which is locked wont drop").copy().withStyle(ChatFormatting.UNDERLINE),
                getFalseBooleanTooltip("When dying, gear (jumpsuits and ankle monitors) which is locked will drop even if its locked")));

        DESCRIPTIONS.add(new DescriptionHolder("PLAYERS_ATTACK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Players cant attack prisoners and officers").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("onlyPrisoners:").setStyle(Styles.getPrisonStyle(true)).append(Component.literal("Players can only attack prisoners").setStyle(Styles.getPrisonStyle(false))),
                Component.literal("onlyOfficers:").setStyle(Styles.getOfficerStyle(true)).append(Component.literal("Players can only attack officers").setStyle(Styles.getOfficerStyle(false))),
                Component.literal("✓ both:").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Players can attack prisoners and officers").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("PRISONERS_ATTACK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Prisoners cant attack prisoners and officers").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("onlyPrisoners: ").setStyle(Styles.getPrisonStyle(true)).append(Component.literal("Prisoners can only attack prisoners").setStyle(Styles.getPrisonStyle(false)).withStyle(ChatFormatting.UNDERLINE)),
                Component.literal("onlyOfficers: ").setStyle(Styles.getOfficerStyle(true)).append(Component.literal("Prisoners can only attack officers").setStyle(Styles.getOfficerStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Prisoners can attack prisoners and officers").setStyle(Styles.getDarkGreenStyle(false)))));

        DESCRIPTIONS.add(new DescriptionHolder("CAN_PRISONER_ATTACK_PLAYERS_WITHOUT_ROLE",
                getTrueBooleanTooltip("Prisoners will be able to attack players that dont have a role").copy().setStyle(Style.EMPTY.withUnderlined(true)),
                getFalseBooleanTooltip("Prisoners wont be able to attack players that dont have a role")));

        DESCRIPTIONS.add(new DescriptionHolder("ALLOW_BREAKING_TIME_LOCKED_RESTRAINTS",
                getTrueBooleanTooltip("Restraints that are time locked can be broken but wont drop"),
                getFalseBooleanTooltip("Restraints that are time locked will be unbreakable").copy().setStyle(Style.EMPTY.withUnderlined(true))));

        DESCRIPTIONS.add(new DescriptionHolder("SHOW_INFO_MESSAGES",
                getTrueBooleanTooltip("Shows info messages (usually yellow) when interacting with certain items").copy().setStyle(Style.EMPTY.withUnderlined(true)),
                getFalseBooleanTooltip("Doesnt shows info messages (usually yellow) when interacting with certain items")));

        DESCRIPTIONS.add(new DescriptionHolder("SHOW_SUCCESS_MESSAGES",
                getTrueBooleanTooltip("Shows success messages (usually green) when an interaction is successful").copy().setStyle(Style.EMPTY.withUnderlined(true)),
                getFalseBooleanTooltip("Doesnt show success messages (usually green) when an interaction is successful")));

        DESCRIPTIONS.add(new DescriptionHolder("SHOW_FAIL_MESSAGES",
                getTrueBooleanTooltip("Shows fail messages (usually red) when an interaction fails").copy().setStyle(Style.EMPTY.withUnderlined(true)),
                getFalseBooleanTooltip("Doesnt shows fail messages (usually red) when an interaction fails")));

        DESCRIPTIONS.add(new DescriptionHolder("PUT_PLAYERS_IN_CREATIVE_WHEN_ANTIGOD_RESTRAINTS_TIME_LOCK_RUNS_OUT",
                getTrueBooleanTooltip("Once the time lock on a restraint which has the Anti-God modifier runs out the player will be put in to creative mode").copy().setStyle(Style.EMPTY.withUnderlined(true)),
                getFalseBooleanTooltip("Once the time lock on a restraint which has the Anti-God modifier runs out the player will stay in surival mode")));

        DESCRIPTIONS.add(new DescriptionHolder("ALLOW_EXECUTE_COMMANDS_WHILE_RESTRAINED",
                getTrueBooleanTooltip("While restrained players will be able to use commands like normal"),
                getFalseBooleanTooltip("While restrained players wont able to execute any type of commands").copy().withStyle(Style.EMPTY.withUnderlined(true))));

        DESCRIPTIONS.add(new DescriptionHolder("ALLOW_PRISONERS_EXECUTE_COMMANDS",
                getTrueBooleanTooltip("While restrained players will be able to use commands like normal"),
                getFalseBooleanTooltip("While restrained players wont able to execute any type of commands").copy().withStyle(Style.EMPTY.withUnderlined(true))));


        /* -- PREFIX SETTINGS DESCRIPTIONS -- */

        DESCRIPTIONS.add(new DescriptionHolder("SHOW_ROLE_PREFIX",
                getTrueBooleanTooltip("Players with a role will have a prefix before their name with showing the role"),
                getFalseBooleanTooltip("Players with a role wont have a prefix before their name showing the role")));

        DESCRIPTIONS.add(new DescriptionHolder("ROLE_PREFIX_BOLD",
                getTrueBooleanTooltip("If Role Prefixes are enabled they will be displayed in bold text"),
                getFalseBooleanTooltip("If Role Prefixes are enabled they will be displayed in normal text")));

        DESCRIPTIONS.add(new DescriptionHolder("PRISONER_ROLE_PREFIX",
                getTrueBooleanTooltip("If Role Prefixes are enabled they will be displayed in bold text"),
                getFalseBooleanTooltip("If Role Prefixes are enabled they will be displayed in normal text")));

        DESCRIPTIONS.add(new DescriptionHolder("OFFICER_ROLE_PREFIX",
                Component.literal("[INMATE] ").setStyle(Styles.getPrisonStyle(true)).append(Component.literal("").setStyle(Styles.getPrisonStyle(false))).withStyle(ChatFormatting.UNDERLINE),
                Component.literal("[PRISONER] ").setStyle(Styles.getPrisonStyle(true)).append(Component.literal("").setStyle(Styles.getPrisonStyle(false))),
                Component.literal("[CONVICT] ").setStyle(Styles.getPrisonStyle(true)).append(Component.literal("").setStyle(Styles.getPrisonStyle(false))),
                Component.literal("[D-CLASS] ").setStyle(Styles.getPrisonStyle(true)).append(Component.literal("SCP reference :p").setStyle(Styles.getPrisonStyle(false)))));

        DESCRIPTIONS.add(new DescriptionHolder("OFFICER_ROLE_PREFIX",
                Component.literal("[OFFICER] ").setStyle(Styles.getOfficerStyle(true)).append(Component.literal("Officer prefix").setStyle(Styles.getOfficerStyle(false))).withStyle(ChatFormatting.UNDERLINE),
                Component.literal("[POLICE] ").setStyle(Styles.getOfficerStyle(true)).append(Component.literal("Police prefix").setStyle(Styles.getOfficerStyle(false))),
                Component.literal("[COP] ").setStyle(Styles.getOfficerStyle(true)).append(Component.literal("Cop prefix").setStyle(Styles.getOfficerStyle(false))),
                Component.literal("[GUARD] ").setStyle(Styles.getOfficerStyle(true)).append(Component.literal("Guard prefix").setStyle(Styles.getOfficerStyle(false)))));


        /* -- PLAYER SETTINGS DESCRIPTIONS -- */

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PLAYERS_JUMPSUIT_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Players can not put on nor take off jumpsuits of other players").setStyle(Styles.getDarkRedStyle(false))).withStyle(ChatFormatting.UNDERLINE),
                Component.literal("⬆️ onlyPutOn: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only put jumpsuits on other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("⬇️ onlyTakeOff: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only put jumpsuits off other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Players can put jumpsuits on and off other players").setStyle(Styles.getDarkGreenStyle(false)))));

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PLAYERS_ANKLE_MONITOR_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Players can not put ankle monitors on or off other players").setStyle(Styles.getDarkRedStyle(false))).withStyle(ChatFormatting.UNDERLINE),
                Component.literal("🔓 onlyPutOn: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only put ankle monitors on other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyTakeOff: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only put ankle monitors off other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Players can put ankle monitors on and off other players").setStyle(Styles.getDarkGreenStyle(false)))));

        DESCRIPTIONS.add(new DescriptionHolder("PLAYERS_OWN_JUMPSUIT_LOCK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Players can not lock nor unlock their own jumpsuit").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔓 onlyUnlock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only lock their own jumpsuit").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyLock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only unlock their own jumpsuit").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Players can lock and unlock their own jumpsuit").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("PLAYERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Players can not lock nor unlock their own ankle monitor").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔓 onlyUnlock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only lock their own ankle monitor").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyLock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only unlock their own ankle monitor").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Players can lock and unlock their own ankle monitor").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PLAYERS_JUMPSUIT_LOCK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Players can not lock nor unlock the jumpsuit of other players").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔓 onlyUnlock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only lock the jumpsuit of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyLock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only unlock the jumpsuit of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Players can lock and unlock the jumpsuit of other players").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PLAYERS_ANKLE_MONITOR_LOCK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Players can not lock nor unlock the ankle monitor of other players").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔓 onlyUnlock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only lock the ankle monitor of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyLock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only unlock the ankle monitor of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Players can lock and unlock the ankle monitor of other players").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("PLAYERS_OWN_TRACKER_BINDING_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Players can not bind nor unbind their own ankle monitor").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔗 onlyBind: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only bind their own ankle monitor").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("⛓️‍💥 onlyUnbind: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only unbind their own ankle monitor").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Players can bind and unbind their own ankle monitor").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PLAYERS_TRACKER_BINDING_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Players can not bind nor unbind the ankle monitor of other players").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔗 onlyBind: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only bind the ankle monitor of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("⛓️‍💥 onlyUnbind: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Players can only unbind the ankle monitor of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Players can bind and unbind the ankle monitor of other players").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));


        /* PRISONER SETTINGS DESCRIPTIONS */

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PRISONERS_JUMPSUIT_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Prisoners can not put on nor take off jumpsuits of other players").setStyle(Styles.getDarkRedStyle(false))).withStyle(ChatFormatting.UNDERLINE),
                Component.literal("⬆️ onlyPutOn: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only put jumpsuits on other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("⬇️ onlyTakeOff: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only put jumpsuits off other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Prisoners can put jumpsuits on and off other players").setStyle(Styles.getDarkGreenStyle(false)))));

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PRISONERS_ANKLE_MONITOR_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Prisoners can not put ankle monitors on or off other players").setStyle(Styles.getDarkRedStyle(false))).withStyle(ChatFormatting.UNDERLINE),
                Component.literal("🔓 onlyPutOn: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only put ankle monitors on other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyTakeOff: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only put ankle monitors off other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Prisoners can put ankle monitors on and off other players").setStyle(Styles.getDarkGreenStyle(false)))));

        DESCRIPTIONS.add(new DescriptionHolder("PRISONERS_OWN_JUMPSUIT_LOCK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Prisoners can not lock nor unlock their own jumpsuit").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔓 onlyUnlock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only lock their own jumpsuit").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyLock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only unlock their own jumpsuit").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Prisoners can lock and unlock their own jumpsuit").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("PRISONERS_OWN_ANKLE_MONITOR_LOCK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Prisoners can not lock nor unlock their own ankle monitor").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔓 onlyUnlock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only lock their own ankle monitor").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyLock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only unlock their own ankle monitor").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Prisoners can lock and unlock their own ankle monitor").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PRISONERS_JUMPSUIT_LOCK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Prisoners can not lock nor unlock the jumpsuit of other players").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔓 onlyUnlock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only lock the jumpsuit of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyLock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only unlock the jumpsuit of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Prisoners can lock and unlock the jumpsuit of other players").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PRISONERS_ANKLE_MONITOR_LOCK_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Prisoners can not lock nor unlock the ankle monitor of other players").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔓 onlyUnlock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only lock the ankle monitor of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("🔒 onlyLock: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only unlock the ankle monitor of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Prisoners can lock and unlock the ankle monitor of other players").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("PRISONERS_OWN_TRACKER_BINDING_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Prisoners can not bind nor unbind their own ankle monitor").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔗 onlyBind: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only bind their own ankle monitor").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("⛓️‍💥 onlyUnbind: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only unbind their own ankle monitor").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Prisoners can bind and unbind their own ankle monitor").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));

        DESCRIPTIONS.add(new DescriptionHolder("OTHER_PRISONERS_TRACKER_BINDING_BEHAVIOR",
                Component.literal("× none: ").setStyle(Styles.getDarkRedStyle(true)).append(Component.literal("Prisoners can not bind nor unbind the ankle monitor of other players").setStyle(Styles.getDarkRedStyle(false))),
                Component.literal("🔗 onlyBind: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only bind the ankle monitor of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("⛓️‍💥 onlyUnbind: ").setStyle(Styles.getWhiteStyle(true)).append(Component.literal("Prisoners can only unbind the ankle monitor of other players").setStyle(Styles.getWhiteStyle(false))),
                Component.literal("✓ both: ").setStyle(Styles.getDarkGreenStyle(true)).append(Component.literal("Prisoners can bind and unbind the ankle monitor of other players").setStyle(Styles.getDarkGreenStyle(false))).withStyle(ChatFormatting.UNDERLINE)));
    }


    private static Component getTrueBooleanTooltip(String trueText) {
        Component trueDescComponent = Component.literal(trueText).setStyle(Styles.getTrueStyle(false));
        Component trueState = Component.literal("True: ").setStyle(Styles.getTrueStyle(true));
        return trueState.copy().append(trueDescComponent.copy());
    }

    private static Component getFalseBooleanTooltip(String falseText) {
        Component falseDescComponent = Component.literal(falseText).setStyle(Styles.getFalseStyle(false));
        Component falseState = Component.literal("False: ").setStyle(Styles.getFalseStyle(true));
        return falseState.copy().append(falseDescComponent.copy());
    }


}
