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

package de.Keyle.MyPet.skill.skills;

import de.Keyle.MyPet.api.entity.MyPet;
import de.Keyle.MyPet.api.skill.UpgradeComputer;
import de.Keyle.MyPet.api.skill.skills.Slow;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class SlowImpl implements Slow {

    private static Random random = new Random();

    protected UpgradeComputer<Integer> chance = new UpgradeComputer<>(0);
    protected UpgradeComputer<Integer> duration = new UpgradeComputer<>(0);
    private MyPet myPet;

    public SlowImpl(MyPet myPet) {
        this.myPet = myPet;
    }

    public MyPet getMyPet() {
        return myPet;
    }

    public boolean isActive() {
        return chance.getValue() > 0 && duration.getValue() > 0;
    }

    @Override
    public void reset() {
        chance.removeAllUpgrades();
        duration.removeAllUpgrades();
    }

    public String toPrettyString() {
        return "" + ChatColor.GOLD + chance.getValue() + ChatColor.RESET
                + "% -> " + ChatColor.GOLD + duration.getValue() + ChatColor.RESET + "sec";
    }

    public boolean trigger() {
        return random.nextDouble() <= chance.getValue() / 100.;
    }

    public UpgradeComputer<Integer> getDuration() {
        return duration;
    }

    public UpgradeComputer<Integer> getChance() {
        return chance;
    }

    public void apply(LivingEntity target) {
        PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, duration.getValue() * 20, 1, false);
        target.addPotionEffect(effect);
    }

    @Override
    public String toString() {
        return "SlowImpl{" +
                "chance=" + chance +
                ", duration=" + duration +
                '}';
    }
}