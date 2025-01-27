package com.stereowalker.survive.util.data;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.google.gson.JsonObject;
import com.stereowalker.survive.Survive;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class EntityTemperatureData extends JsonData {
    private static final Marker ENTITY_TEMPERATURE_DATA = MarkerManager.getMarker("ENTITY_TEMPERATURE_DATA");
    
	private ResourceLocation entityID;
	private final float temperatureModifier;
	private final float range;
	
	public EntityTemperatureData(ResourceLocation blockID, JsonObject object) {
		super(object);
		String NOTHING = "nothing";
		String TEMPERATURE_MODIFIER = "temperature_modifier";
		String RANGE = "range";
		
		float temperatureModifierIn = 0;
		float rangeIn = 0;
		
		this.entityID = blockID;
		if(object.entrySet().size() != 0) {
			String workingOn = NOTHING;
			try {
				if(object.has(TEMPERATURE_MODIFIER) && object.get(TEMPERATURE_MODIFIER).isJsonPrimitive()) {
					workingOn = TEMPERATURE_MODIFIER;
					temperatureModifierIn = object.get(TEMPERATURE_MODIFIER).getAsFloat();
					workingOn = NOTHING;
				}
				
				if(object.has(RANGE) && object.get(RANGE).isJsonPrimitive()) {
					workingOn = RANGE;
					rangeIn = object.get(RANGE).getAsFloat();
					workingOn = NOTHING;
				}
			} catch (ClassCastException e) {
				Survive.getInstance().getLogger().warn(ENTITY_TEMPERATURE_DATA, "Loading entity temperature data $s from JSON: Parsing element %s: element was wrong type!", e, blockID, workingOn);
			} catch (NumberFormatException e) {
				Survive.getInstance().getLogger().warn(ENTITY_TEMPERATURE_DATA, "Loading entity temperature data $s from JSON: Parsing element %s: element was an invalid number!", e, blockID, workingOn);
			}
		}
		
		if (rangeIn > 5) {
			Survive.getInstance().getLogger().warn(ENTITY_TEMPERATURE_DATA, "Loading entity temperature data $s from JSON: Range should not be greater that 5", blockID);
			rangeIn = 5;
		}
		
		if (rangeIn < 0) {
			Survive.getInstance().getLogger().warn(ENTITY_TEMPERATURE_DATA, "Loading entity temperature data $s from JSON: Range should not be less than 0", blockID);
			rangeIn = 0;
		}
		
		
		this.temperatureModifier = temperatureModifierIn;
		this.range = rangeIn;
		
	}

	public ResourceLocation getItemID() {
		return entityID;
	}

	/**
	 * @return the temperatureModifier
	 */
	public float getTemperatureModifier() {
		return temperatureModifier;
	}

	/**
	 * @return the range
	 */
	public float getRange() {
		return range;
	}

	@Override
	public CompoundNBT serialize() {
		// TODO Auto-generated method stub
		return null;
	}
}
