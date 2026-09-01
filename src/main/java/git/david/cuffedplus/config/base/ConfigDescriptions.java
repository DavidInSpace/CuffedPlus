package git.david.cuffedplus.config.base;

import git.david.cuffedplus.constants.Styles;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;

public record ConfigDescriptions() {
    public static ArrayList<DescriptionHolder> DESCRIPTIONS = new ArrayList<>();

    public static void initDescriptions() {
        DESCRIPTIONS.add(new DescriptionHolder("KEEP_LOCKED_GEAR_ON_DEATH",
                getTrueBooleanTooltip("When dying, gear (jumpsuits and ankle monitors) which is locked wont drop"),
                getFalseBooleanTooltip("When dying, gear (jumpsuits and ankle monitors) which is locked will drop even if the gear is locked")));

        DESCRIPTIONS.add(new DescriptionHolder("PLAYERS_ATTACK_BEHAVIOR",
                Component.literal("none: ").withStyle(ChatFormatting.BOLD).append(Component.literal("Players can attack everyone")).withStyle(ChatFormatting.BLUE),
                Component.literal("onlyPrisoners:").withStyle(ChatFormatting.BOLD).append(Component.literal("Players can attack everyone")).withStyle(ChatFormatting.BLUE),
                Component.literal("onlyOfficers:").withStyle(ChatFormatting.BOLD).append(Component.literal("Players can attack everyone")).withStyle(ChatFormatting.BLUE),
                Component.literal("both:").withStyle(ChatFormatting.BOLD).append(Component.literal("Players can attack everyone")).withStyle(ChatFormatting.BLUE)));

        DESCRIPTIONS.add(new DescriptionHolder("PRISONERS_ATTACK_BEHAVIOR",
                Component.literal("none: ").withStyle(ChatFormatting.BOLD).append(Component.literal("Prisoners can attack everyone")).withStyle(ChatFormatting.BLUE),
                Component.literal("onlyPrisoners:").withStyle(ChatFormatting.BOLD).append(Component.literal("Prisoners can attack everyone")).withStyle(ChatFormatting.BLUE),
                Component.literal("onlyOfficers:").withStyle(ChatFormatting.BOLD).append(Component.literal("Prisoners can attack everyone")).withStyle(ChatFormatting.BLUE),
                Component.literal("both:").withStyle(ChatFormatting.BOLD).append(Component.literal("Prisoners can attack everyone")).withStyle(ChatFormatting.BLUE)));
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
