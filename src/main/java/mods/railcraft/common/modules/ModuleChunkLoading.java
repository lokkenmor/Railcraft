/*******************************************************************************
 * Copyright (c) CovertJaguar, 2011-2016
 * http://railcraft.info
 *
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 ******************************************************************************/
package mods.railcraft.common.modules;

import mods.railcraft.api.core.RailcraftModule;
import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.beta.EnumMachineBeta;
import mods.railcraft.common.carts.EnumCart;
import mods.railcraft.common.core.Railcraft;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.util.misc.ChunkManager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
@RailcraftModule("chunk_loading")
public class ModuleChunkLoading extends RailcraftModulePayload {

    ModuleChunkLoading() {
        setEnabledEventHandler(new ModuleEventHandler() {

            @Override
            public void preInit() {
                super.preInit();
                ForgeChunkManager.setForcedChunkLoadingCallback(Railcraft.getMod(), ChunkManager.getInstance());
                MinecraftForge.EVENT_BUS.register(ChunkManager.getInstance());

                EnumMachineAlpha alpha = EnumMachineAlpha.WORLD_ANCHOR;
                if (RailcraftConfig.isSubBlockEnabled(alpha.getTag())) {
                    Block block = RailcraftBlocks.registerBlockMachineAlpha();
                    if (block != null && RailcraftConfig.canCraftAnchors()) {
                        CraftingPlugin.addRecipe(alpha.getItem(),
                                "gog",
                                "dpd",
                                "gog",
                                'd', "gemDiamond",
                                'g', "ingotGold",
                                'p', Items.ender_pearl,
                                'o', new ItemStack(Blocks.obsidian));
                    }
                }

                alpha = EnumMachineAlpha.PERSONAL_ANCHOR;
                if (RailcraftConfig.isSubBlockEnabled(alpha.getTag())) {
                    Block block = RailcraftBlocks.registerBlockMachineAlpha();
                    if (block != null && RailcraftConfig.canCraftPersonalAnchors()) {
                        CraftingPlugin.addRecipe(alpha.getItem(),
                                "gog",
                                "dpd",
                                "gog",
                                'd', "gemEmerald",
                                'g', "ingotGold",
                                'p', Items.ender_pearl,
                                'o', new ItemStack(Blocks.obsidian));
                    }
                }

                alpha = EnumMachineAlpha.PASSIVE_ANCHOR;
                if (RailcraftConfig.isSubBlockEnabled(alpha.getTag())) {
                    Block block = RailcraftBlocks.registerBlockMachineAlpha();
                    if (block != null && RailcraftConfig.canCraftPassiveAnchors()) {
                        CraftingPlugin.addRecipe(alpha.getItem(),
                                "gog",
                                "dpd",
                                "gog",
                                'd', "dyeCyan",
                                'g', "ingotGold",
                                'p', Items.ender_pearl,
                                'o', new ItemStack(Blocks.obsidian));
                    }
                }

                alpha = EnumMachineAlpha.ADMIN_ANCHOR;
                if (RailcraftConfig.isSubBlockEnabled(alpha.getTag())) {
                    RailcraftBlocks.registerBlockMachineAlpha();
                }

                EnumMachineBeta beta = EnumMachineBeta.SENTINEL;
                if (RailcraftConfig.isSubBlockEnabled(beta.getTag())) {
                    Block block = RailcraftBlocks.registerBlockMachineBeta();
                    if (block != null) {
                        ItemStack stack = beta.getItem();
                        if (RailcraftConfig.canCraftAnchors()) {
                            CraftingPlugin.addRecipe(stack,
                                    " p ",
                                    " o ",
                                    "ogo",
                                    'g', "ingotGold",
                                    'p', Items.ender_pearl,
                                    'o', new ItemStack(Blocks.obsidian));
                        }
                    }
                }

                // Define Anchor Cart
                EnumCart cart = EnumCart.ANCHOR;
                if (EnumMachineAlpha.WORLD_ANCHOR.isAvailable() && cart.setup()) {
                    ItemStack anchor = EnumMachineAlpha.WORLD_ANCHOR.getItem();
                    if (RailcraftConfig.canCraftPersonalAnchors()) {
                        CraftingPlugin.addRecipe(cart.getCartItem(),
                                "A",
                                "M",
                                'A', anchor,
                                'M', Items.minecart);
                    }
                    cart.setContents(anchor);
                }


                // Define Personal Anchor Cart
                cart = EnumCart.ANCHOR_PERSONAL;
                if (EnumMachineAlpha.PERSONAL_ANCHOR.isAvailable() && cart.setup()) {
                    ItemStack anchor = EnumMachineAlpha.PERSONAL_ANCHOR.getItem();
                    if (RailcraftConfig.canCraftPersonalAnchors()) {
                        CraftingPlugin.addRecipe(cart.getCartItem(),
                                "A",
                                "M",
                                'A', anchor,
                                'M', Items.minecart);
                    }
                    cart.setContents(anchor);
                }

                // Define Admin Anchor Cart
                cart = EnumCart.ANCHOR_ADMIN;
                if (EnumMachineAlpha.ADMIN_ANCHOR.isAvailable() && cart.setup()) {
                    ItemStack anchor = EnumMachineAlpha.ADMIN_ANCHOR.getItem();
                    cart.setContents(anchor);
                }
            }
        });
    }
}
