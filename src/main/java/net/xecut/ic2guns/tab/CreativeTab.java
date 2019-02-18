package net.xecut.ic2guns.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {

    public CreativeTab (int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem () {
        return new ItemStack(Items.CAKE);
    }
}
