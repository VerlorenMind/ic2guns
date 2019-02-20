package net.xecut.ic2guns.recipe;

import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.xecut.ic2guns.item.IC2GunsItemBasic;
import net.xecut.ic2guns.item.IC2GunsItems;
import net.xecut.ic2guns.item.ItemMagazine;

public class IC2GunsRecipes {

    public static void init () {
        IRecipeInputFactory input = Recipes.inputFactory;
        IC2GunsItemBasic cartridgeEmpty = IC2GunsItems.CARTRIDGE_EMPTY.instance;
        IC2GunsItemBasic cartridge = IC2GunsItems.CARTRIDGE.instance;
        IC2GunsItemBasic barrel = IC2GunsItems.BARREL.instance;
        IC2GunsItemBasic handguard = IC2GunsItems.HANDGUARD.instance;

        /* EMPTY CARTRIDGE */
        addExtrudingRecipe(input.forStack(IC2Items.getItem("casing", "copper")),
                new ItemStack(cartridgeEmpty, 2));


        /* DUST FROM CARTRIDGE */
        addMaceratorRecipe(input.forStack(new ItemStack(cartridgeEmpty, 4)),
                IC2Items.getItem("dust", "copper"));
        addMaceratorRecipe(input.forStack(new ItemStack(cartridge, 4)),
                IC2Items.getItem("dust", "copper"));

        /* MAGAZINE */
        addExtrudingRecipe(input.forStack(IC2Items.getItem("casing", "steel")),
                ItemMagazine.makeMagazine(1, 0));

        /* FILL MAGAZINE */
        addCanningRecipe(input.forStack(ItemMagazine.makeMagazine(1, 0)),
                input.forStack(new ItemStack(cartridge, 20)),
                ItemMagazine.makeMagazine(1, ItemMagazine.MAX_AMMO));

        /* BARREL */
        addExtrudingRecipe(input.forOreDict("plateSteel", 3),
                new ItemStack(barrel));

        /* HANDGUARD */
        addExtrudingRecipe(input.forStack(IC2Items.getItem("crafting", "alloy"), 2),
                new ItemStack(handguard));

    }

    private static void addExtrudingRecipe (IRecipeInput in, ItemStack out) {
        Recipes.metalformerExtruding.addRecipe(in, null, false, out);
    }

    private static void addMaceratorRecipe (IRecipeInput in, ItemStack out) {
        Recipes.macerator.addRecipe(in, null, false, out);
    }

    private static void addCanningRecipe (IRecipeInput container, IRecipeInput filler, ItemStack out) {
        Recipes.cannerBottle.addRecipe(container, filler, out, false);
    }

}
