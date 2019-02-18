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
public class IC2GunsItemsRegister {

    // Create instances
    public static void preInit () {
        for (IC2GunsItems item : IC2GunsItems.values()) {
            try {
                item.instance = (IC2GunsItemBasic) item.cl.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void registerItems (RegistryEvent.Register<Item> event) {
        for (IC2GunsItems item : IC2GunsItems.values()) {
            event.getRegistry().register(item.instance);
            if (item.oreDictName != null)
                item.registerOreDictionary();
        }
    }

    @SubscribeEvent
    public static void registerRenders (ModelRegistryEvent event) {
        for (IC2GunsItems item : IC2GunsItems.values())
            registerRender(item.instance);
    }

    private static void registerRender (IC2GunsItemBasic item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
