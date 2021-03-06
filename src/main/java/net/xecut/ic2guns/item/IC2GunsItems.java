package net.xecut.ic2guns.item;

import net.minecraftforge.oredict.OreDictionary;

public enum IC2GunsItems {

    /*****************************************/
    SMALL_GUNPOWDER(ItemSmallGunpowder.class, "tinyDustGunpowder"),
    NUGGET_LEAD(ItemNuggetLead.class, "nuggetLead"),
    CARTRIDGE_EMPTY(ItemCartridgeEmpty.class),
    CARTRIDGE(ItemCartridge.class),
    MAGAZINE(ItemMagazine.class),
    BARREL(ItemBarrel.class),
    BUTTSTOCK(ItemButtstock.class),
    HANDGUARD(ItemHandguard.class),
    LOWER_RECEIVER(ItemLowerReceiver.class),
    UPPER_RECEIVER(ItemUpperReceiver.class),
    RIFLE(ItemRifle.class);
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
