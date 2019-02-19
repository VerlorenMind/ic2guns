package net.xecut.ic2guns;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xecut.ic2guns.item.IC2GunsItemsRegister;
import net.xecut.ic2guns.proxy.ServerProxy;
import net.xecut.ic2guns.recipe.IC2GunsRecipes;
import net.xecut.ic2guns.tab.CreativeTab;
import org.apache.logging.log4j.Logger;

@Mod (modid = Reference.MODID,
      version = Reference.VERSION,
      useMetadata = true)
public class IC2GunsMod {

    public static Logger logger;

    @SidedProxy (clientSide = "net.xecut.ic2guns.proxy.ClientProxy",
                 serverSide = "net.xecut.ic2guns.proxy.ServerProxy")
    public static ServerProxy proxy;

    @Mod.Instance
    public static IC2GunsMod instance;

    public static CreativeTab creativeTab;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        logger = event.getModLog();
        creativeTab = new CreativeTab(CreativeTabs.getNextID(), "ic2guns");
        proxy.preInit(event);
        IC2GunsItemsRegister.preInit();
    }

    @EventHandler
    public void init (FMLInitializationEvent event) {
        proxy.init(event);
        IC2GunsRecipes.init();
    }

    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
