package net.xecut.ic2guns.item;

import net.minecraftforge.oredict.OreDictionary;

public enum IC2GunsItems {

    /*****************************************/
    SMALL_GUNPOWDER(ItemSmallGunpowder.class),
    CARTRIDGE_EMPTY(ItemCartridgeEmpty.class),
    CARTRIDGE(ItemCartridge.class),
    NUGGET_LEAD(ItemNuggetLead.class, "nuggetLead");
    /*****************************************/

    public Class cl;
    public IC2GunsItemBasic instance;
    public String oreDictName = null;

    IC2GunsItems (Class cl) {
        this.cl = cl;
    }

    IC2GunsItems (Class cl, String oreDictName) {
        this(cl);
        this.oreDictName = oreDictName;
    }

    public void registerOreDictionary () {
        OreDictionary.registerOre(oreDictName, instance);
    }
}
