package git.david.cuffedplus.screen;


import com.mojang.blaze3d.systems.RenderSystem;
import git.david.cuffedplus.init.ModNetwork;
import git.david.cuffedplus.items.item.JumpsuitItem;
import git.david.cuffedplus.menu.CuffTableMenu;
import git.david.cuffedplus.net.ApplyJumpsuitSettingsPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CuffTableMenuScreen extends AbstractContainerScreen<CuffTableMenu> {
    private static final ResourceLocation MENU_TEXTURE = ResourceLocation.fromNamespaceAndPath("cuffedplus", "textures/gui/menus/cuffs_menu.png");
    private static final ResourceLocation DOWN_ARROW_TEXTURE = ResourceLocation.fromNamespaceAndPath("cuffedplus", "textures/gui/menus/buttons/arrow_down.png");
    private static final ResourceLocation UP_ARROW_TEXTURE = ResourceLocation.fromNamespaceAndPath("cuffedplus", "textures/gui/menus/buttons/arrow_up.png");

    private static final String[] backTitle = {"Target", "Iron Bars", "Inmate Text", "Test Subject Text", "Letter A", "Letter B", "Letter C", "Letter D", "Letter E", "Letter F", "Letter G", "Letter H"};

    byte number = 0;

    public CuffTableMenuScreen(CuffTableMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    protected void init() {
        super.init();


        //  guiGraphics.drawString(this.font, "Example Text", this.leftPos + 20, this.topPos + 10, 0x404040, false);

        // int itemCount = menu.getBlockEntity().getInventory().getSlots(); // Example value
        // guiGraphics.drawString(this.font, "Slots: " + itemCount, this.leftPos + 10, this.topPos + 25, 0x404040, false);

        this.addRenderableWidget(new ImageButton(
                this.leftPos + 25,
                this.topPos + 15,
                8,
                10,
                0,
                0,
                0,
                UP_ARROW_TEXTURE,
                8,
                10,
                btn -> IncreaseNumber()
        ));

        this.addRenderableWidget(new ImageButton(
                this.leftPos + 25,
                this.topPos + 40,
                8,
                10,
                0,
                0,
                0,
                DOWN_ARROW_TEXTURE,
                8,
                10,
                btn -> DecreaseNumber()
        ));




        this.addRenderableWidget(Button.builder(
                Component.literal("Apply"),
                btn -> applySettings()
        ).bounds(this.leftPos + 52, this.topPos + 37, 30, 17).build());

    }


    private void DecreaseNumber() {
        if (number > 0) {
            number -= 1;
        }
    }

    private void IncreaseNumber() {
        if (number < 20) {
            number += 1;
        }
    }


    private void applySettings() {
        ItemStack stack = this.menu.getSlot(0).getItem();
        Item item = stack.getItem();
       // if (item == AirItem) {}

        if (!(item instanceof JumpsuitItem) || number < 1) {return;}
        // JumpsuitItem.setNumber(stack, number);
        //canCraft = false;
       // if (!(stack.getItem() instanceof JumpsuitItem)) return;

        // Send to server

        ModNetwork.INSTANCE.sendToServer(new ApplyJumpsuitSettingsPacket(number));
        //System.out.println("Item: " + item.getDescriptionId());
    }


    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, MENU_TEXTURE);

        ItemStack stack = this.menu.getSlot(0).getItem(); // Slot 0 = block slot
        Item item = stack.getItem();

        if (item instanceof JumpsuitItem) {
            if (number > 0 && number < backTitle.length) {
                //guiGraphics.drawString(this.font, String.valueOf(number), this.leftPos + 23, this.topPos + 18, 0xFFFFFF, true);
                 guiGraphics.drawString(this.font, backTitle[number], this.leftPos + 23, this.topPos + 30, 0xFFFFFF, true);
            } else {
                guiGraphics.drawString(this.font, "-", this.leftPos + 23, this.topPos + 18, 0xFFFFFF, true);
            }
        }


    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        ItemStack stack = this.menu.getSlot(0).getItem(); // Slot 0 = block slot
            Item item = stack.getItem();
        //    System.out.println("Item in slot 0: " + item.getDescriptionId());

        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
