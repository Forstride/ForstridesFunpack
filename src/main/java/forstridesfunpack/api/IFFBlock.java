package forstridesfunpack.api;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public interface IFFBlock {
    
    Class<? extends ItemBlock> getItemClass();
    IProperty[] getPresetProperties();
    IProperty[] getNonRenderingProperties();
    String getStateName(IBlockState state);
}