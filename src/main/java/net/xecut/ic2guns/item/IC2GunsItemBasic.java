package net.xecut.ic2guns.item;

import net.minecraft.item.Item;
import net.xecut.ic2guns.IC2GunsMod;

public class IC2GunsItemBasic extends Item {

    public IC2GunsItemBasic (String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(IC2GunsMod.creativeTab);
    }

}
