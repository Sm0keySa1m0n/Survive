package com.stereowalker.survive.enchantment;

import java.util.ArrayList;
import java.util.List;

import com.stereowalker.survive.Survive;
import com.stereowalker.survive.config.Config;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.registries.IForgeRegistry;

public class SEnchantments {
	public static List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
	
	public static final Enchantment WARMING = registerTempe("warming", new TempControlEnchantment(Rarity.UNCOMMON, new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET}));
	public static final Enchantment COOLING = registerTempe("cooling", new TempControlEnchantment(Rarity.UNCOMMON, new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET}));
	public static final Enchantment ADJUSTED_WARMING = registerTempe("adjusted_warming", new AutoTempControlEnchantment(Rarity.VERY_RARE, new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET}));
	public static final Enchantment ADJUSTED_COOLING = registerTempe("adjusted_cooling", new AutoTempControlEnchantment(Rarity.VERY_RARE, new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET}));
	public static final Enchantment FEATHERWEIGHT = registerStami("featherweight", new FeatherweightEnchantment(Rarity.UNCOMMON, new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET}));
	public static final Enchantment WEIGHTLESS = registerStami("weightless", new WeightlessEnchantment(Rarity.VERY_RARE, new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET}));
	
	public static Enchantment registerTempe(String name, Enchantment enchantment) {
		if (Config.enable_temperature) {
			return register(name, enchantment);
		} else {
			return enchantment;
		}
	}
	
	public static Enchantment registerStami(String name, Enchantment enchantment) {
		if (Config.enable_stamina) {
			return register(name, enchantment);
		} else {
			return enchantment;
		}
	}
	
	public static Enchantment register(String name, Enchantment enchantment) {
		enchantment.setRegistryName(Survive.getInstance().location(name));
		ENCHANTMENTS.add(enchantment);
		return enchantment;
	}
	
	public static void registerAll(IForgeRegistry<Enchantment> registry) {
		if (!Config.disable_enchantments) {
			for(Enchantment enchantment : ENCHANTMENTS) {
				registry.register(enchantment);
				Survive.getInstance().debug("Enchantment: \""+enchantment.getRegistryName().toString()+"\" registered");
			}
			Survive.getInstance().debug("All Enchantments Registered");
		} else {
			Survive.getInstance().debug("Enchantments not registered due to it being disabled");
		}
	}
}
