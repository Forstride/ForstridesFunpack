/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package forstridesfunpack.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemGrassSeeds extends Item
{
    public ItemGrassSeeds()
    {
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);
        if (player.canPlayerEdit(pos.offset(facing), facing, itemstack)&& worldIn.isAirBlock(pos.up()))
        {
        	if (state.getBlock() == Blocks.DIRT)
        	{
        		worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());

	            if (player instanceof EntityPlayerMP)
	            {
	                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
	            }
	            
	            itemstack.shrink(1);
	            return EnumActionResult.SUCCESS;
        	}
        	else
        	{
        		return EnumActionResult.FAIL;
        	}
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
}