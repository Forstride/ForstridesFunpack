/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package forstridesfunpack.block;

import forstridesfunpack.api.IFFBlock;
import forstridesfunpack.item.ItemFFBlock;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockLattice extends BlockPane implements IFFBlock
{
    // implement ISSBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemFFBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    
    public BlockLattice(Material material)
    {
        super(material, true);
        this.setHardness(0.4F);
        this.setHarvestLevel("axe", 0);
        this.setSoundType(SoundType.WOOD);
    }
}