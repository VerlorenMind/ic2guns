package net.xecut.ic2guns.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.xecut.ic2guns.Reference;

@Mod.EventBusSubscriber (modid = Reference.MODID)
public class IC2GunsItems {

    public static Item smallGunpowder;

    public static void preInit () {
        smallGunpowder = new ItemSmallGunpowder();
    }

    @SubscribeEvent
    public static void registerItems (RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(smallGunpowder);
    }

    @SubscribeEvent
    public static void registerRenders (ModelRegistryEvent event) {
        registerRender(smallGunpowder);
    }

    private static void registerRender (Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
