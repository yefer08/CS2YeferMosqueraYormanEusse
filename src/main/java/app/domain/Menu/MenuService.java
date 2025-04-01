/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Menu;

public class MenuService {

    private final AdminMenu adminMenu;
    private final VeterinarianMenu veterinarianMenu;
    private final SellerMenu sellerMenu;
    private final OwnerMenu ownerMenu;

    public MenuService(AdminMenu adminMenu, VeterinarianMenu veterinarianMenu,
            SellerMenu sellerMenu, OwnerMenu ownerMenu) {
        this.adminMenu = adminMenu;
        this.veterinarianMenu = veterinarianMenu;
        this.sellerMenu = sellerMenu;
        this.ownerMenu = ownerMenu;
    }

    public void showMenu(String role, String ownerId) {
        switch (role.toLowerCase()) {
            case "admin" ->
                adminMenu.showAdminMenu();
            case "veterinarian" ->
                veterinarianMenu.showVeterinarianMenu();
            case "seller" ->
                sellerMenu.showSellerMenu();
            case "owner" ->
                ownerMenu.showOwnerMenu(ownerId);
            default ->
                System.out.println("❌ Rol no reconocido. Contacte con un administrador.");
        }
    }
}

