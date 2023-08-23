package fr.picsou.narutoduel.utils.Gui;

import org.bukkit.event.Listener;

public class GuiManager implements Listener {

 /// @EventHandler
 /// public void onClick(InventoryClickEvent event){
///
 /// Player player = (Player) event.getWhoClicked();
 ///Inventory inv = event.getInventory();
  ///ItemStack current = event.getCurrentItem();
///
  /// if(event.getCurrentItem() == null) return;
///
  /// Main.getInstance().getRegisteredMenus().values().stream()
  ///       .filter(menu -> event.getView().getTitle().equalsIgnoreCase(menu.name(player)))
  ///       .forEach(menu -> {
  ///         menu.onclick(player, inv, current, event.getSlot(), event.getClick());
  ///         event.setCancelled(true);
  ///       });
///
  ///}
///
  ///public void addMenu(GuiBuilder m){
  ///Main.getInstance().getRegisteredMenus().put(m.getClass(), m);
  ///}

  ///public void removeMenu(GuiBuilder m) {Main.getInstance().getRegisteredMenus().remove(m.getClass(), m);}

  ///public void open(Player player, Class<? extends GuiBuilder> gClass){

  ///if(!Main.getInstance().getRegisteredMenus().containsKey(gClass)) return;

  ///GuiBuilder menu = Main.getInstance().getRegisteredMenus().get(gClass);
  ///Inventory inv = Bukkit.createInventory(null, menu.getSize(), menu.name(player));
  ///try {
  /// menu.contents(player, inv);
  ///} catch (Exception e) {
  /// throw new RuntimeException(e);
  ///}

  ///new BukkitRunnable() {

  /// @Override
  /// public void run() {
  ///   player.openInventory(inv);
  /// }

  ///}.runTaskLater(Main.getInstance(), 1);
///
  ///}

}