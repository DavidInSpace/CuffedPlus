package git.david.cuffedplus.logic;

public class AnkleMonitorLogic {
    /*ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    ItemStack hoveringItem;
    int hoveringSlot;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side.isServer() && event.phase == TickEvent.Phase.START) {
            ItemStack itemInChest = event.player.getItemBySlot(EquipmentSlot.CHEST);
            if (itemInChest.getItem() instanceof AnkleMonitorItem) {
                assert itemInChest.getTag() != null;
                if (itemInChest.getTag().getBoolean("HighVisibility")) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 0, false, false));
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteraction(PlayerInteractEvent.RightClickItem event) {
        ItemStack itemInHand = event.getEntity().getItemInHand(event.getHand());
        if (itemInHand.getItem() instanceof ArmorItem && !(itemInHand.getItem() instanceof AnkleMonitorItem) && itemInHand.getOrCreateTag().getBoolean("Locked")) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void containerEvent(ContainerScreenEvent event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (event.getContainerScreen().getSlotUnderMouse() != null && event.getContainerScreen().getSlotUnderMouse().getItem().getOrCreateTag().getBoolean("Locked")) {
                hoveringSlot = event.getContainerScreen().getSlotUnderMouse().getSlotIndex();
                hoveringItem = event.getContainerScreen().getSlotUnderMouse().getItem();
            }
        }
    }

    @SubscribeEvent
    public void screenEvent(InputEvent.MouseButton event) {
        Player player = Minecraft.getInstance().player;
        if ((event.getButton() == 0 || event.getButton() == 1) && player != null) {
            if (hoveringItem != null && hoveringItem.getItem() instanceof AnkleMonitorItem && hoveringSlot == 38) {
                player.playSound(SoundEvents.CHAIN_FALL, 1, (float) Math.random());
                player.displayClientMessage(Component.literal("Your ankle monitor is locked").withStyle(ChatFormatting.RED), true);
                event.setCanceled(true);
            }
        }
    } */

}
