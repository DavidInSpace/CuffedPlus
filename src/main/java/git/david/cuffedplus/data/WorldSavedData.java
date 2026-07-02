package git.david.cuffedplus.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

public class WorldSavedData extends SavedData {

    private boolean CanPrisonersTakeJumpsuitsOff = true;
    private boolean CanPrisonersPutJumpsuitsOn = true;
    private boolean CanPrisonersTakeJumpsuitsOffOthers = true;
    private boolean CanPrisonersPutJumpsuitsOnOthers = true;

    public static WorldSavedData create() {
        return new WorldSavedData();
    }

    public static WorldSavedData load(CompoundTag tag) {
        WorldSavedData data = create();
        boolean CanPrisonersTakeJumpsuitsOffBool = tag.getBoolean("can_prisoners_take_jumpsuits_off");
        boolean CanPrisonersPutJumpsuitsOnBool = tag.getBoolean("can_prisoners_put_jumpsuits_on");
        boolean CanPrisonersTakeJumpsuitsOffOthersBool = tag.getBoolean("can_prisoners_take_jumpsuits_off_others");
        boolean CanPrisonersPutJumpsuitsOnOthersBool = tag.getBoolean("can_prisoners_put_jumpsuits_on_others");
        data.CanPrisonersTakeJumpsuitsOff = CanPrisonersTakeJumpsuitsOffBool;
        data.CanPrisonersPutJumpsuitsOn = CanPrisonersPutJumpsuitsOnBool;
        data.CanPrisonersTakeJumpsuitsOffOthers = CanPrisonersTakeJumpsuitsOffOthersBool;
        data.CanPrisonersPutJumpsuitsOnOthers = CanPrisonersPutJumpsuitsOnOthersBool;
        return data;
    }

    public @NotNull CompoundTag save(CompoundTag tag) {
        tag.putBoolean("can_prisoners_take_jumpsuits_off", CanPrisonersTakeJumpsuitsOff);
        tag.putBoolean("can_prisoners_put_jumpsuits_on", CanPrisonersPutJumpsuitsOn);
        tag.putBoolean("can_prisoners_take_jumpsuits_off_others", CanPrisonersTakeJumpsuitsOffOthers);
        tag.putBoolean("can_prisoners_put_jumpsuits_on_others", CanPrisonersPutJumpsuitsOnOthers);
        return tag;
    }
    // TODO: Figure out how to get values of the settings from the client side when there is no access to the server
    public static WorldSavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                WorldSavedData::load,
                WorldSavedData::create, "cuffed_plus_settings"
        );
    }

    public boolean getCanPrisonersTakeJumpsuitsOff() {
        return this.CanPrisonersTakeJumpsuitsOff;
    }

    public boolean getCanPrisonersPutJumpsuitsOn() {
        return this.CanPrisonersPutJumpsuitsOn;
    }

    public boolean getCanPrisonersTakeJumpsuitsOffOthers() {
        return this.CanPrisonersTakeJumpsuitsOffOthers;
    }

    public boolean getCanPrisonersPutJumpsuitsOnOthers() {
        return this.CanPrisonersPutJumpsuitsOnOthers;
    }



    public void setCanPrisonersTakeJumpsuitsOff(boolean state) {
        this.CanPrisonersTakeJumpsuitsOff = state;
        this.setDirty();
    }

    public void setCanPrisonersPutJumpsuitsOn(boolean state) {
        this.CanPrisonersPutJumpsuitsOn = state;
        this.setDirty();
    }

    public void setCanPrisonersTakeJumpsuitsOffOthers(boolean state) {
        this.CanPrisonersTakeJumpsuitsOffOthers = state;
        this.setDirty();
    }

    public void setCanPrisonersPutJumpsuitsOnOthers(boolean state) {
        this.CanPrisonersPutJumpsuitsOnOthers = state;
        this.setDirty();
    }


}
