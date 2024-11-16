package net.pyroevents.blockUpdates

import net.minestom.server.MinecraftServer
import net.minestom.server.event.instance.InstanceChunkLoadEvent
import net.minestom.server.event.instance.InstanceTickEvent
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerBlockPlaceEvent
import net.minestom.server.instance.Chunk

object BlockUpdates {
    fun init() {
        val node = MinecraftServer.getGlobalEventHandler()

        node.addListener(InstanceTickEvent::class.java) { e ->
            BlockUpdateManager.from(e.instance).tick()
        }
        node.addListener(PlayerBlockBreakEvent::class.java) { e ->
            BlockUpdateManager.from(e.instance).scheduleUpdates(e.blockPosition, BlockUpdateInfo.DESTROY)
        }
        node.addListener(PlayerBlockPlaceEvent::class.java) { e ->
            BlockUpdateManager.from(e.instance).scheduleUpdates(e.blockPosition, BlockUpdateInfo.PLACE)
        }
        node.addListener(InstanceChunkLoadEvent::class.java) { e ->
            val minY = e.chunk.minSection * Chunk.CHUNK_SECTION_SIZE
            val maxY = e.chunk.maxSection * Chunk.CHUNK_SECTION_SIZE
            val minX = e.chunk.chunkX * Chunk.CHUNK_SIZE_X
            val minZ = e.chunk.chunkZ * Chunk.CHUNK_SIZE_Z

            BlockUpdateManager.from(e.instance)


        }
    }
}