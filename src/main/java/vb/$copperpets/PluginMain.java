package vb.$copperpets;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;

public class PluginMain extends JavaPlugin implements Listener {

	private static PluginMain instance;

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		try {
			Object $66c620e56c3faf40a753768c870efe4d = null;
			$66c620e56c3faf40a753768c870efe4d = new org.bukkit.inventory.ShapedRecipe(
					new org.bukkit.NamespacedKey(((org.bukkit.plugin.Plugin) (Object) PluginMain.getInstance()),
							"xp_bottle"),
					PluginMain.getNamedItemWithLore(((org.bukkit.Material) org.bukkit.Material.COPPER_INGOT),
							ChatColor.translateAlternateColorCodes('&', "&2xp pet"),
							new ArrayList(Arrays.asList("Right click to get XP"))));
			((org.bukkit.inventory.ShapedRecipe) (Object) $66c620e56c3faf40a753768c870efe4d)
					.shape(((java.lang.String[]) ((Collection) PluginMain.getInstance().getConfig().get("xppet.recipe"))
							.toArray(new java.lang.String[]{})));
			((org.bukkit.inventory.ShapedRecipe) (Object) $66c620e56c3faf40a753768c870efe4d)
					.setIngredient("b".charAt(0), ((org.bukkit.Material) org.bukkit.Material.DIAMOND_BLOCK));
			((org.bukkit.inventory.ShapedRecipe) (Object) $66c620e56c3faf40a753768c870efe4d)
					.setIngredient("a".charAt(0), ((org.bukkit.Material) org.bukkit.Material.STICK));
			org.bukkit.Bukkit.addRecipe(((org.bukkit.inventory.Recipe) (Object) $66c620e56c3faf40a753768c870efe4d));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] commandArgs) {
		return true;
	}

	public static void procedure(String procedure, List procedureArgs) throws Exception {
	}

	public static Object function(String function, List functionArgs) throws Exception {
		return null;
	}

	public static List createList(Object obj) {
		if (obj instanceof List) {
			return (List) obj;
		}
		List list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			int length = java.lang.reflect.Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				list.add(java.lang.reflect.Array.get(obj, i));
			}
		} else if (obj instanceof Collection<?>) {
			list.addAll((Collection<?>) obj);
		} else if (obj instanceof Iterator) {
			((Iterator<?>) obj).forEachRemaining(list::add);
		} else {
			list.add(obj);
		}
		return list;
	}

	public static void createResourceFile(String path) {
		Path file = getInstance().getDataFolder().toPath().resolve(path);
		if (Files.notExists(file)) {
			try (InputStream inputStream = PluginMain.class.getResourceAsStream("/" + path)) {
				Files.createDirectories(file.getParent());
				Files.copy(inputStream, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static PluginMain getInstance() {
		return instance;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event1(org.bukkit.event.player.PlayerInteractEvent event) throws Exception {
		if (PluginMain.checkEquals(((org.bukkit.inventory.ItemStack) event.getItem()),
				PluginMain.getNamedItemWithLore(((org.bukkit.Material) org.bukkit.Material.COPPER_INGOT),
						ChatColor.translateAlternateColorCodes('&', "&2xp pet"),
						new ArrayList(Arrays.asList("Right click to get XP"))))) {
			if ((PluginMain.checkEquals(((org.bukkit.event.block.Action) event.getAction()),
					((org.bukkit.event.block.Action) org.bukkit.event.block.Action.RIGHT_CLICK_AIR))
					|| PluginMain.checkEquals(((org.bukkit.event.block.Action) event.getAction()),
							((org.bukkit.event.block.Action) org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK)))) {
				if (PluginMain.checkEquals(
						((java.lang.Object) (Object) ((boolean) ((org.bukkit.inventory.Inventory) ((org.bukkit.inventory.InventoryHolder) (Object) ((org.bukkit.entity.Player) event
								.getPlayer())).getInventory())
										.containsAtLeast(
												new org.bukkit.inventory.ItemStack(
														((org.bukkit.Material) org.bukkit.Material.DIAMOND_BLOCK)),
												((int) (1d))))),
						((java.lang.Object) (Object) true))) {
					((org.bukkit.inventory.Inventory) ((org.bukkit.inventory.InventoryHolder) (Object) ((org.bukkit.entity.Player) event
							.getPlayer())).getInventory()).removeItem(((org.bukkit.inventory.ItemStack[]) new ArrayList(
									Arrays.asList(new org.bukkit.inventory.ItemStack(
											((org.bukkit.Material) org.bukkit.Material.DIAMOND_BLOCK), ((int) (1d)))))
													.toArray(new org.bukkit.inventory.ItemStack[]{})));
					((org.bukkit.entity.Player) event.getPlayer()).giveExpLevels(((int) (100d)));
				}
			}
		} else {
		}
	}

	public static org.bukkit.inventory.ItemStack getNamedItemWithLore(Material material, String name,
			List<String> lore) {
		org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(material);
		org.bukkit.inventory.meta.ItemMeta itemMeta = item.getItemMeta();
		if (itemMeta != null) {
			itemMeta.setDisplayName(name);
			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
		}
		return item;
	}

	public static boolean checkEquals(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}
		return o1 instanceof Number && o2 instanceof Number
				? ((Number) o1).doubleValue() == ((Number) o2).doubleValue()
				: o1.equals(o2);
	}
}
