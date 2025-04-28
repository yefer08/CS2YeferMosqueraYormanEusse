/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package app;

import app.Menu.LoginMenu;
import app.Menu.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeterinaryApplication implements CommandLineRunner {
    
    @Autowired
    private LoginMenu menu;
    @Autowired
    private LoginService loginService;
    
   public static void main(String[] args) {
		SpringApplication.run(VeterinaryApplication.class, args);
                
	}

   
    @Override
    public void run(String... args) throws Exception {
       
  
        menu.showLoginMenu(); //implementacipn del menu
        
    }
}
