/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
    package app.adapter;

import app.Entities.UserEntity;
import app.domain.models.Owner;
import app.domain.models.Seller;
import app.domain.models.User;
import app.domain.models.Veterinarian;
import app.infrastructure.repositories.UserRepository;
import app.ports.Userport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component 
public class UserAdapter implements Userport {

    @Autowired 
    private UserRepository userrepository;

    @Override
    public User findById(String id) {
        Optional<UserEntity> userEntityOptional = userrepository.findById(id);
        return userEntityOptional.map(this::convertToDomain)
                .orElse(null); 
    }

    @Override
    public void save(User newUser) {
        UserEntity userEntity = convertToEntity(newUser);
        userrepository.save(userEntity);
    }

    
    private UserEntity convertToEntity(User user) {
        return new UserEntity(
            user.getId(),
            user.getAge(),
            user.getUsername(),
            user.getPassword(),
            user.getRole(),
            user.getName()
        );
    }

    
    private User convertToDomain(UserEntity entity) {
        switch (entity.getRole().toLowerCase()) {
            case "veterinarian":
                return new Veterinarian(entity.getId(), entity.getName(), 0, entity.getUsername(), entity.getPassword(), entity.getRole());
            case "owner":
                return new Owner(entity.getId(), entity.getName(), 0, entity.getUsername(), entity.getPassword(), entity.getRole());
            case "seller":
                return new Seller(entity.getId(), entity.getName(), 0, entity.getUsername(), entity.getPassword(), entity.getRole());
            default:
                return new User(entity.getId(), entity.getName(), 0, entity.getUsername(), entity.getPassword(), entity.getRole()) {};
        }
    }
    
  @Override
public Owner findByid(String id) {
    Optional<UserEntity> userEntityOptional = userrepository.findById(id);

    if (userEntityOptional.isPresent()) {
        UserEntity userEntity = userEntityOptional.get();

        
        if ("owner".equalsIgnoreCase(userEntity.getRole())) {
            return new Owner(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAge(), 
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRole()
            );
        }
    }

    throw new RuntimeException("Error: No se encontró un dueño con ID " + id);
}
    @Override
    public Veterinarian findVeterinarianById(String id) {
        Optional<UserEntity> userEntityOptional = userrepository.findById(id);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            if ("veterinarian".equalsIgnoreCase(userEntity.getRole())) {
                return new Veterinarian(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getAge(),
                        userEntity.getUsername(),
                        userEntity.getPassword(),
                        userEntity.getRole()
                );
            }
        }

        throw new RuntimeException("Error: No se encontró un veterinario con ID " + id);
    }
    
    public UserEntity convertToUserEntity(Veterinarian veterinarian) {
        UserEntity userEntity = new UserEntity();

        
        userEntity.setId(veterinarian.getId());
        userEntity.setName(veterinarian.getName());
        userEntity.setAge(veterinarian.getAge());
        userEntity.setUsername(veterinarian.getUsername());
        userEntity.setPassword(veterinarian.getPassword());
        userEntity.setRole("veterinarian"); 
        
        
        return userEntity;
    }
    
    public UserEntity convertToUserEntity(Owner owner) {
        return new UserEntity(
                owner.getId(),
                owner.getAge(),
                owner.getUsername(),
                owner.getPassword(),
                owner.getName(),
                "owner" 
        );
    }
    
    public UserEntity convertToUserEntity(Seller seller) {
        return new UserEntity(
                seller.getId(),
                seller.getAge(),
                seller.getUsername(),
                seller.getPassword(),
                seller.getName(),
                "seller" 
        );
    }
    
    

}
