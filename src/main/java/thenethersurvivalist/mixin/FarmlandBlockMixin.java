package thenethersurvivalist.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import thenethersurvivalist.TheNetherSurvivalistSettings;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin extends Block {

    public FarmlandBlockMixin(Settings settings) {
        super(settings);
    }

    /**
     * @author DukeEdivad05
     * @reason FarmableCropBlockOnNether
     */
    @Overwrite
    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
        return TheNetherSurvivalistSettings.FarmlandMoisture;
    }
}
