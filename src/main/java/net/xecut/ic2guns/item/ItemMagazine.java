package net.xecut.ic2guns.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagazine extends IC2GunsItemBasic {

    private static final String AMMO_KEY = "ammo";
    public static final int MAX_AMMO = 20;

    public ItemMagazine () {
        super("magazine");
        setMaxStackSize(16);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tooltip.ammo_label") + " " + getAmmo(stack));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean showDurabilityBar (ItemStack stack) {
        int ammo = getAmmo(stack);
        return (ammo != 0) && (ammo != MAX_AMMO);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getDurabilityForDisplay (ItemStack stack) {
        return 1.0 - getAmmo(stack) / (double) MAX_AMMO;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName (ItemStack stack) {
        return getAmmo(stack) == 0 ? I18n.format("item.magazine.name.empty") :
                super.getItemStackDisplayName(stack);
    }

    public static int getAmmo (ItemStack stack) {
        int ammo = 0;
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            if (nbt.hasKey(AMMO_KEY))
                ammo = nbt.getInteger(AMMO_KEY);
        }
        return ammo;
    }

    public static void setAmmo (ItemStack stack, int value) {
        NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        nbt.setInteger(AMMO_KEY, value);
        stack.setTagCompound(nbt);
    }

    public static ItemStack makeMagazine (int amount, int ammo) {
        ItemStack magazine = new ItemStack(IC2GunsItems.MAGAZINE.instance, amount);
        setAmmo(magazine, ammo);
        return magazine;
    }

}
