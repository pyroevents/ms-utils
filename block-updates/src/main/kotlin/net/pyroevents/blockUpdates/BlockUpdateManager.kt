package net.pyroevents.blockUpdates

import net.minestom.server.coordinate.BlockVec
import net.minestom.server.instance.Instance
import java.util.UUID

/**
 * A majority of this is referenced from VRI but modified to be kotlin.
 */
class BlockUpdateManager(
    val instance: Instance
) {
    companion object {
        private val managers: MutableMap<UUID, BlockUpdateManager> = mutableMapOf()
        fun from(instance: Instance): BlockUpdateManager {
            return managers.computeIfAbsent(instance.uniqueId) {
                BlockUpdateManager(instance)
            }
        }
    }

    fun tick() {

    }

    fun scheduleUpdates(vec: BlockVec, info: BlockUpdateInfo) {

    }
}