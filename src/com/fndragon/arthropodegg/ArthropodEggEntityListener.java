package com.fndragon.arthropodegg;

import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Randy
 * @since 1.0
 * 
 * All the event listeners related to ArthropodEgg plugin. Handles
 * each case that is currently configured for handling.
 */
public class ArthropodEggEntityListener implements Listener {

	private ArthropodEgg plugin;
	
	public ArthropodEggEntityListener( ArthropodEgg instance ) {
		plugin = instance;
	}
		
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDeath( EntityDeathEvent event ) {
		Player targetPlayer = event.getEntity().getKiller();
		if( null == targetPlayer ) {
			return;
		}
		
		Short currentEntityID = event.getEntity().getType().getTypeId();
		if( false == plugin.getConfig().getShortList("eggEntityIDList").contains( currentEntityID ) ) {
			return;
		}

		// Check for a baby animal
		if( event.getEntity() instanceof Ageable )
		{
			Ageable ageableEntity = (Ageable) event.getEntity();
			if( ageableEntity.isAdult() == false ) {
				return;  // NOPE.
			}
		}
		
		// Check the player's currently equipped weapon
		ItemStack handstack = targetPlayer.getItemInHand();
		// Get the map of enchantments on that item
		Map<Enchantment,Integer> itemEnchants = handstack.getEnchantments();
		if(itemEnchants.isEmpty()) {
			return;
		}
		
		// Check if one enchantment is BaneOfArthropods
		if( null == itemEnchants.get( org.bukkit.enchantments.Enchantment.DAMAGE_ARTHROPODS ) )
		{
			return;
		}
		
		double randomNum = Math.random();
		double eggArthropodPercentage = plugin.getConfig().getDouble( "eggArthropodPercentage" );
		double eggLootingPercentage = plugin.getConfig().getDouble( "eggLootingPercentage" );
		double levelOfArthropod = handstack.getEnchantmentLevel(org.bukkit.enchantments.Enchantment.DAMAGE_ARTHROPODS);
		double levelOfLooting = handstack.getEnchantmentLevel(org.bukkit.enchantments.Enchantment.LOOT_BONUS_MOBS);
		
		double targetPercentage = (eggArthropodPercentage * levelOfArthropod) + (eggLootingPercentage * levelOfLooting);
		if( plugin.getConfig().getBoolean("eggDebug")) {
			targetPlayer.sendMessage( "Arth[" + levelOfArthropod + "], Loot[" + levelOfLooting + "]");
			targetPlayer.sendMessage( "Total =" + targetPercentage * 100 + "%, random% is " + randomNum * 100 );
		}

		// Check if egg should be spawned
		if( randomNum < targetPercentage )
		{
			ItemStack item;
			switch (event.getEntityType()) {
				//Passive Mobs
				case BAT:
					item = new ItemStack(Material.BAT_SPAWN_EGG,1);
					break;
				case CHICKEN:
					item = new ItemStack(Material.CHICKEN_SPAWN_EGG,1);
					break;
				case COD:
					item = new ItemStack(Material.COD_SPAWN_EGG,1);
					break;
				case COW:
					item = new ItemStack(Material.COW_SPAWN_EGG,1);
					break;
				case DONKEY:
					item = new ItemStack(Material.DONKEY_SPAWN_EGG,1);
					break;
				case HORSE:
					item = new ItemStack(Material.HORSE_SPAWN_EGG,1);
					break;
				case MUSHROOM_COW:
					item = new ItemStack(Material.MOOSHROOM_SPAWN_EGG,1);
					break;
				case MULE:
					item = new ItemStack(Material.MULE_SPAWN_EGG,1);
					break;
				case OCELOT:
					item = new ItemStack(Material.OCELOT_SPAWN_EGG,1);
					break;
				case PARROT:
					item = new ItemStack(Material.PARROT_SPAWN_EGG,1);
					break;
				case PIG:
					item = new ItemStack(Material.PIG_SPAWN_EGG,1);
					break;
				case PUFFERFISH:
					item = new ItemStack(Material.PUFFERFISH_SPAWN_EGG,1);
					break;
				case RABBIT:
					item = new ItemStack(Material.RABBIT_SPAWN_EGG,1);
					break;
				case SALMON:
					item = new ItemStack(Material.SALMON_SPAWN_EGG,1);
					break;
				case SHEEP:
					item = new ItemStack(Material.SHEEP_SPAWN_EGG,1);
					break;
				case SKELETON_HORSE:
					item = new ItemStack(Material.SKELETON_HORSE_SPAWN_EGG,1);
					break;
				case SQUID:
					item = new ItemStack(Material.SQUID_SPAWN_EGG,1);
					break;
				case TROPICAL_FISH:
					item = new ItemStack(Material.TROPICAL_FISH_SPAWN_EGG,1);
					break;
				case TURTLE:
					item = new ItemStack(Material.TURTLE_SPAWN_EGG,1);
					break;
				case VILLAGER:
					item = new ItemStack(Material.VILLAGER_SPAWN_EGG,1);
					break;
				case ZOMBIE_HORSE:
					item = new ItemStack(Material.ZOMBIE_HORSE_SPAWN_EGG,1);
					break;
				// Neutral or Hostile Mobs
				case BLAZE:
					item = new ItemStack(Material.BLAZE_SPAWN_EGG,1);
					break;
				case CAVE_SPIDER:
					item = new ItemStack(Material.CAVE_SPIDER_SPAWN_EGG,1);
					break;
				case CREEPER:
					item = new ItemStack(Material.CREEPER_SPAWN_EGG,1);
					break;
				case DOLPHIN:
					item = new ItemStack(Material.DOLPHIN_SPAWN_EGG,1);
					break;
				case DROWNED:
					item = new ItemStack(Material.DROWNED_SPAWN_EGG,1);
					break;
				case ELDER_GUARDIAN:
					item = new ItemStack(Material.ELDER_GUARDIAN_SPAWN_EGG,1);
					break;
				case ENDERMAN:
					item = new ItemStack(Material.ENDERMAN_SPAWN_EGG,1);
					break;
				case ENDERMITE:
					item = new ItemStack(Material.ENDERMITE_SPAWN_EGG,1);
					break;
				case EVOKER:
					item = new ItemStack(Material.EVOKER_SPAWN_EGG,1);
					break;
				case GHAST:
					item = new ItemStack(Material.GHAST_SPAWN_EGG,1);
					break;
				case GUARDIAN:
					item = new ItemStack(Material.GUARDIAN_SPAWN_EGG,1);
					break;
				case HUSK:
					item = new ItemStack(Material.HUSK_SPAWN_EGG,1);
					break;
				case LLAMA:
					item = new ItemStack(Material.LLAMA_SPAWN_EGG,1);
					break;
				case MAGMA_CUBE:
					item = new ItemStack(Material.MAGMA_CUBE_SPAWN_EGG,1);
					break;
				case PHANTOM:
					item = new ItemStack(Material.PHANTOM_SPAWN_EGG,1);
					break;
				case POLAR_BEAR:
					item = new ItemStack(Material.POLAR_BEAR_SPAWN_EGG,1);
					break;
				case SHULKER:
					item = new ItemStack(Material.SHULKER_SPAWN_EGG,1);
					break;
				case SILVERFISH:
					item = new ItemStack(Material.SILVERFISH_SPAWN_EGG,1);
					break;
				case SKELETON:
					item = new ItemStack(Material.SKELETON_SPAWN_EGG,1);
					break;
				case SLIME:
					item = new ItemStack(Material.SLIME_SPAWN_EGG,1);
					break;
				case SPIDER:
					item = new ItemStack(Material.SPIDER_SPAWN_EGG,1);
					break;
				case STRAY:
					item = new ItemStack(Material.STRAY_SPAWN_EGG,1);
					break;
				case VEX:
					item = new ItemStack(Material.VEX_SPAWN_EGG,1);
					break;
				case VINDICATOR:
					item = new ItemStack(Material.VINDICATOR_SPAWN_EGG,1);
					break;
				case WITCH:
					item = new ItemStack(Material.WITCH_SPAWN_EGG,1);
					break;
				case WITHER_SKELETON:
					item = new ItemStack(Material.WITCH_SPAWN_EGG,1);
					break;
				case WOLF:
					item = new ItemStack(Material.WOLF_SPAWN_EGG,1);
					break;
				case ZOMBIE:
					item = new ItemStack(Material.ZOMBIE_SPAWN_EGG,1);
					break;
				case PIG_ZOMBIE:
					item = new ItemStack(Material.ZOMBIE_PIGMAN_SPAWN_EGG,1);
					break;
				case ZOMBIE_VILLAGER:
					item = new ItemStack(Material.ZOMBIE_VILLAGER_SPAWN_EGG,1);
					break;
				default:
					item = new ItemStack(Material.EGG,1);
					break;
			}
			if( plugin.getConfig().getBoolean("eggRemoveDrops")) {
				event.getDrops().clear();
				event.setDroppedExp(0);
			}
			event.getDrops().add( item );
			if( plugin.getConfig().getBoolean("eggDebug")) {
				targetPlayer.sendMessage( "Egg generated." );
			}
		}		
	}
}
