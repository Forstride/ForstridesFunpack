package forstridesfunpack.item;

import com.google.common.collect.ImmutableSet;

import forstridesfunpack.api.IFFBlock;
import forstridesfunpack.util.BlockStateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFFBlock extends ItemBlock
{
    public IFFBlock ffBlock;

    public ItemFFBlock(Block block)
    {
        super(block);
        if (block instanceof IFFBlock)
        {
            this.ffBlock = (IFFBlock)block;
        }
        else
        {
            throw new IllegalArgumentException("ItemFFBlock must be created with a block implementing IFFBlock");
        }
        this.setHasSubtypes(true);
    }

    // define the items which will appear in the creative tab (called by ItemBlock class)
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        if (this.isInCreativeTab(tab))
        {
            ImmutableSet<IBlockState> presets = BlockStateUtils.getBlockPresets(this.block);
            if (presets.isEmpty()) {
                subItems.add(new ItemStack(this.block, 1, 0));
            } else {
                for (IBlockState state : presets) {
                    subItems.add(new ItemStack(this.block, 1, this.block.getMetaFromState(state)));
                }
            }
        }
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        ImmutableSet<IBlockState> presets = BlockStateUtils.getBlockPresets(this.block);
        if (presets.isEmpty())
        {
            return super.getUnlocalizedName();
        }
        else
        {
            int meta = stack.getMetadata();
            IBlockState oldState = block.getStateFromMeta(meta);
            IBlockState newState = BlockStateUtils.getPresetState(oldState);

            return super.getUnlocalizedName() + "." + ffBlock.getStateName(newState);
        }
    }
}
