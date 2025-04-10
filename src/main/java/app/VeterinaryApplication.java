/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeterinaryApplication {

   public static void main(String[] args) {
		SpringApplication.run(VeterinaryApplication.class, args);
                 menu.showLoginMenu(); //implementacipn del menu
       	 	adminMenu.showAdminMenu();
        	ownerMenu.showOwnerMenu(ownerId);
        	sellerMenu.showSellerMenu();
        	veterianrianMenu.showVeterinarianMenu();
        	menuService.showMenu(role, ownerId);
	}
}
