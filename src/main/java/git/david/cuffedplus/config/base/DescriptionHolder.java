package git.david.cuffedplus.config.base;

import net.minecraft.network.chat.Component;

public class DescriptionHolder {

    private final String id;
    public final Component[] descriptions;

    public DescriptionHolder(String id, Component desc1, Component desc2, Component desc3, Component desc4) {
        this.id = id;
        this.descriptions = new Component[]{
                desc1,
                Component.literal("\n\n").append(desc2),
                Component.literal("\n\n").append(desc3),
                Component.literal("\n\n").append(desc4),
        };
    }

    public DescriptionHolder(String id, Component desc1, Component desc2, Component desc3) {
        this.id = id;
        this.descriptions = new Component[]{
                desc1,
                Component.literal("\n\n").append(desc2),
                Component.literal("\n\n").append(desc3),
        };
    }

    public DescriptionHolder(String id, Component desc1, Component desc2) {
        this.id = id;
        this.descriptions = new Component[]{
                desc1,
                Component.literal("\n\n").append(desc2),
        };
    }

    public DescriptionHolder(String id, Component desc1) {
        this.id = id;
        this.descriptions = new Component[]{
                desc1,
        };
    }

    public String getID() {
        return this.id;
    }

}
