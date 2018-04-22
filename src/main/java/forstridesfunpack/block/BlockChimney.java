/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package forstridesfunpack.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import forstridesfunpack.api.IFFBlock;
import forstridesfunpack.item.ItemFFBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChimney extends Block implements IFFBlock
{
	public static final PropertyBool SMOKING = PropertyBool.create("smoking");
    protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
    protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
	
    // implement ISSBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemFFBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {SMOKING}; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    
    public BlockChimney(Material material, MapColor mapColor, SoundType soundType, int harvestLevel)
    {
        super(material, mapColor);
        this.setHarvestLevel("pickaxe", harvestLevel);
        this.setSoundType(soundType);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SMOKING, false));
    }
    
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState blockState = worldIn.getBlockState(pos.down());
        Block block = blockState.getBlock();
        
        return state.withProperty(SMOKING, Boolean.valueOf(block == Blocks.FIRE || block instanceof BlockChimney && blockState.getValue(SMOKING)));
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] { SMOKING });
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(SMOKING, Boolean.valueOf(meta == 1));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Boolean)state.getValue(SMOKING)).booleanValue() ? 1 : 0;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{ 
		if (stateIn.getValue(SMOKING) == true)
        {
			for (int i = 0; i < 12; i++)
			{
	            double d0 = (double)pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) * 0.2D);
	            double d1 = (double)pos.getY() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) * 0.2D);
	            double d2 = (double)pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) * 0.2D);
	            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
        }
	}
    
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        if (face == EnumFacing.UP)
        {
            return BlockFaceShape.BOWL;
        }
        else
        {
            return face == EnumFacing.DOWN ? BlockFaceShape.BOWL : BlockFaceShape.SOLID;
        }
    }
}