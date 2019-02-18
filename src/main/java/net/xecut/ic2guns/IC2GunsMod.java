package net.xecut.ic2guns;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xecut.ic2guns.item.IC2GunsItems;
import net.xecut.ic2guns.proxy.ServerProxy;
import org.apache.logging.log4j.Logger;

@Mod (modid = Reference.MODID,
      version = Reference.VERSION,
      useMetadata = true)
public class IC2GunsMod {

    private static Logger logger;

    @SidedProxy (clientSide = "net.xecut.ic2guns.proxy.ClientProxy",
                 serverSide = "net.xecut.ic2guns.proxy.ServerProxy")
    public static ServerProxy proxy;

    @Mod.Instance
    public static IC2GunsMod instance;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        IC2GunsItems.preInit();
    }

    @EventHandler
    public void init (FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
