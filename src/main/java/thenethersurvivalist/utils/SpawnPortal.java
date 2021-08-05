package thenethersurvivalist.utils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.server.network.SpawnLocating;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.level.ServerWorldProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Random;

import static net.minecraft.block.ChestBlock.FACING;

public class SpawnPortal {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void setupSpawn(ServerWorld world, ServerWorldProperties serverWorldProperties, boolean bonusChest, boolean debugWorld, boolean bl) {
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        if (!bl) {
            serverWorldProperties.setSpawnPos(BlockPos.ORIGIN.up(chunkGenerator.getSpawnHeight()), 0.0F);
        } else if (debugWorld) {
            serverWorldProperties.setSpawnPos(BlockPos.ORIGIN.up(), 0.0F);
        } else {
            BiomeSource biomeSource = chunkGenerator.getBiomeSource();
            Random random = new Random(world.getSeed());
            BlockPos blockPos = biomeSource.locateBiome(0, world.getSeaLevel(), 0, 256, (biome) -> {
                return biome.getSpawnSettings().isPlayerSpawnFriendly();
            }, random);
            ChunkPos chunkPos = blockPos == null ? new ChunkPos(0, 0) : new ChunkPos(blockPos);
            if (blockPos == null) {
                LOGGER.warn("Unable to find spawn biome");
            }

            boolean bl2 = false;
            Iterator var11 = BlockTags.VALID_SPAWN.values().iterator();

            while(var11.hasNext()) {
                Block block = (Block)var11.next();
                if (biomeSource.getTopMaterials().contains(block.getDefaultState())) {
                    bl2 = true;
                    break;
                }
            }

            BlockPos spawnPos = chunkPos.getStartPos().add(8, chunkGenerator.getSpawnHeight(), 8);
            int i = 0;
            int j = 0;
            int k = 0;
            int l = -1;

            for(int n = 0; n < 1024; ++n) {
                if (i > -16 && i <= 16 && j > -16 && j <= 16) {
                    BlockPos blockPos2 = SpawnLocating.findServerSpawnPoint(world, new ChunkPos(chunkPos.x + i, chunkPos.z + j), bl2);
                    if (blockPos2 != null) {
                        spawnPos = blockPos2;
                        break;
                    }
                }

                if (i == j || i < 0 && i == -j || i > 0 && i == 1 - j) {
                    int o = k;
                    k = -l;
                    l = o;
                }

                i += k;
                j += l;
            }

            if (spawnPos.getY() < 64 || spawnPos.getY() > 110) {
                spawnPos = new BlockPos(spawnPos.getX(), new Random().nextInt(46) + 64, spawnPos.getZ());
            }

            serverWorldProperties.setSpawnPos(spawnPos, 0.0F);


            if (bonusChest) {
                int portalType = new Random().nextInt(2) + 1;
                PortalCommand.place(world, spawnPos, portalType);
                ConfiguredFeature<?, ?> configuredFeature = ConfiguredFeatures.BONUS_CHEST;
                world.setBlockState(spawnPos.add(4, -1, 2), Blocks.CHEST.getDefaultState().with(FACING, Direction.SOUTH));
                LootableContainerBlockEntity.setLootTable(world, random, spawnPos.add(4, -1 , 2), LootTables.SPAWN_BONUS_CHEST);
            } else {
                int portalType = new Random().nextInt(3) + 1;
                PortalCommand.place(world, spawnPos, portalType);
            }

        }
    }
}
