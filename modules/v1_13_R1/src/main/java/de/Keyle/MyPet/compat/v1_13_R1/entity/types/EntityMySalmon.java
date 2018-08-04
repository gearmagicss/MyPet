/*
 * This file is part of MyPet
 *
 * Copyright © 2011-2018 Keyle
 * MyPet is licensed under the GNU Lesser General Public License.
 *
 * MyPet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyPet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.Keyle.MyPet.compat.v1_13_R1.entity.types;

import de.Keyle.MyPet.MyPetApi;
import de.Keyle.MyPet.api.compat.ParticleCompat;
import de.Keyle.MyPet.api.entity.EntitySize;
import de.Keyle.MyPet.api.entity.MyPet;
import de.Keyle.MyPet.compat.v1_13_R1.entity.EntityMyPet;
import net.minecraft.server.v1_13_R1.*;

@EntitySize(width = 0.7F, height = 0.4f)
public class EntityMySalmon extends EntityMyPet {

    private static final DataWatcherObject<Boolean> fromBucketWatcher = DataWatcher.a(EntityMySalmon.class, DataWatcherRegistry.i);

    public EntityMySalmon(World world, MyPet myPet) {
        super(EntityTypes.SALMON, world, myPet);
    }

    @Override
    protected String getDeathSound() {
        return "entity.salmon.death";
    }

    @Override
    protected String getHurtSound() {
        return "entity.salmon.flop";
    }

    protected String getLivingSound() {
        return "entity.salmon.ambient";
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!isInWater() && this.random.nextBoolean()) {
            MyPetApi.getPlatformHelper().playParticleEffect(myPet.getLocation().get().add(0, 0.7, 0), ParticleCompat.WATER_SPLASH.get(), 0.2F, 0.2F, 0.2F, 0.5F, 10, 20);
        }
    }

    protected void initDatawatcher() {
        super.initDatawatcher();
        this.datawatcher.register(fromBucketWatcher, false);
    }
}