/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.plugins.forestry;

import forestry.api.storage.IBackpackDefinition;
import mods.railcraft.api.core.items.IMinecartItem;
import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.detector.BlockDetector;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.beta.EnumMachineBeta;
import mods.railcraft.common.items.ItemSignalBlockSurveyor;
import mods.railcraft.common.items.ItemSignalTuner;
import mods.railcraft.common.blocks.tracks.TrackTools;
import mods.railcraft.common.fluids.FluidContainers;
import mods.railcraft.common.items.ItemCrowbar;
import mods.railcraft.common.items.ItemCrowbarSteel;
import mods.railcraft.common.items.RailcraftItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMinecart;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Optional;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
@Optional.Interface(iface = "forestry.api.storage.IBackpackDefinition", modid = "Forestry")
public class TrackmanBackpack extends BaseBackpack implements IBackpackDefinition {

    private static TrackmanBackpack instance;

    public static TrackmanBackpack getInstance() {
        if (instance == null)
            instance = new TrackmanBackpack();
        return instance;
    }

    protected TrackmanBackpack() {
    }

    public void setup() {
        addItem(ItemCrowbar.getItem());
        addItem(ItemCrowbarSteel.getItem());
        addItem(ItemSignalBlockSurveyor.getItem());
        addItem(ItemSignalTuner.getItem());

        for (ResourceLocation id : Block.blockRegistry.getKeys()) {
            Block block = Block.blockRegistry.getObject(id);
            if (block == null) continue;
            if (TrackTools.isRailBlock(block))
                addItem(block);
        }

        for (ResourceLocation id : Item.itemRegistry.getKeys()) {
            Item item = Item.itemRegistry.getObject(id);
            if (item instanceof ItemMinecart || item instanceof IMinecartItem)
                addItem(item);
        }

        addItem(FluidContainers.getCreosoteOilBottle());
        addItem(FluidContainers.getCreosoteOilBucket());
        addItem(FluidContainers.getCreosoteOilCell());
        addItem(FluidContainers.getCreosoteOilCan());
        addItem(FluidContainers.getCreosoteOilRefactory());
        addItem(FluidContainers.getCreosoteOilWax());

        addItem(EnumMachineAlpha.WORLD_ANCHOR.getItem());
        addItem(EnumMachineAlpha.PERSONAL_ANCHOR.getItem());
        addItem(EnumMachineBeta.SENTINEL.getItem());

        addItem(RailcraftItem.rail);
        addItem(RailcraftItem.railbed);
        addItem(RailcraftItem.tie);
        addItem(RailcraftItem.signalLamp);
        addItem(RailcraftItem.circuit);
        addItem(RailcraftItem.signalLabel);
        addItem(RailcraftItem.whistleTuner);
        addItem(RailcraftItem.magGlass);
        addItem(RailcraftItem.goggles);
        addItem(RailcraftItem.overalls);

        addItem(RailcraftBlocks.getBlockMachineGamma());
        addItem(RailcraftBlocks.getBlockElevator());
        addItem(RailcraftBlocks.getBlockSignal());
        addItem(BlockDetector.getBlock());
    }

    @Override
    public String getKey() {
        return "TRACKMAN";
    }

    @Override
    public int getPrimaryColour() {
        return 0x0094FF;
    }

    @Override
    public int getSecondaryColour() {
        return 0xFFFFFF;
    }

}
