package net.xecut.ic2guns.recipe;

import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.xecut.ic2guns.item.IC2GunsItemBasic;
import net.xecut.ic2guns.item.IC2GunsItems;

public class IC2GunsRecipes {

    public static void init () {
        IRecipeInputFactory input = Recipes.inputFactory;
        IC2GunsItemBasic cartridgeEmpty = IC2GunsItems.CARTRIDGE_EMPTY.instance;
        IC2GunsItemBasic cartridge = IC2GunsItems.CARTRIDGE.instance;

        addExtrudingRecipe(input.forOreDict("plateCopper"),
                new ItemStack(cartridgeEmpty, 4));
        addExtrudingRecipe(input.forStack(IC2Items.getItem("casing", "copper")),
                new ItemStack(cartridgeEmpty, 2));


        addMaceratorRecipe(input.forStack(new ItemStack(cartridgeEmpty, 4)),
                IC2Items.getItem("dust", "copper"));
        addMaceratorRecipe(input.forStack(new ItemStack(cartridge, 4)),
                IC2Items.getItem("dust", "copper"));
    }

    private static void addExtrudingRecipe (IRecipeInput in, ItemStack out) {
        Recipes.metalformerExtruding.addRecipe(in, null, false, out);
    }

    private static void addMaceratorRecipe (IRecipeInput in, ItemStack out) {
        Recipes.macerator.addRecipe(in, null, false, out);
    }

}
