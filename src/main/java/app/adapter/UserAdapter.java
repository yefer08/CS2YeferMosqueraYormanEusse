package app.adapter;

import app.Converted.UserConverter;
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
    public User findById(long id) {
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
                return new Veterinarian( entity.getName(), entity.getAge(), entity.getUsername(), entity.getPassword(), entity.getRole());
            case "owner":
                return new Owner(entity.getName(), entity.getAge(), entity.getUsername(), entity.getPassword(), entity.getRole());
            case "seller":
                return new Seller( entity.getName(), entity.getAge(), entity.getUsername(), entity.getPassword(), entity.getRole());
            default:
                throw new IllegalArgumentException("Rol no reconocido: " + entity.getRole());
        }
    }


    @Override
    public Owner findByid(long id) {
        Optional<UserEntity> userEntityOptional = userrepository.findById(id);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            if ("owner".equalsIgnoreCase(userEntity.getRole())) {
                return new Owner(
                        
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
    public Veterinarian findVeterinarianById(long id) {
        Optional<UserEntity> userEntityOptional = userrepository.findById(id);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            if ("veterinarian".equalsIgnoreCase(userEntity.getRole())) {
                return new Veterinarian(
                        
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
              
                owner.getAge(),
                owner.getUsername(),
                owner.getPassword(),
                owner.getRole(),
                owner.getName()
        );
    }

    public UserEntity convertToUserEntity(Seller seller) {
        return new UserEntity(
                
                seller.getAge(),
                seller.getUsername(),
                seller.getPassword(),
                seller.getRole(),
                seller.getName()
        );
    }

    @Override
    public User findByUsername(String username) {
        UserEntity userEntity = userrepository.findByUsername(username).orElse(null);

        if (userEntity == null) {
            return null;
        }

        return (User) UserConverter.convertToDomainUser(userEntity);
    }
}
