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
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());  // 👈 Este es el que faltaba o estaba mal seteado
        entity.setAge(user.getAge());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        entity.setName(user.getName());
        return entity;
    
    }

    private User convertToDomain(UserEntity entity) {
        switch (entity.getRole().toLowerCase()) {
            case "veterinarian":
                return new Veterinarian(entity.getId(),entity.getName(), entity.getAge(), entity.getUsername(), entity.getPassword(), entity.getRole());
            case "owner":
                return new Owner(entity.getId(),entity.getName(), entity.getAge(), entity.getUsername(), entity.getPassword(), entity.getRole());
            case "seller":
                return new Seller(entity.getId(), entity.getName(), entity.getAge(), entity.getUsername(), entity.getPassword(), entity.getRole());
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

    public Optional<UserEntity> findVeterinarianEntityById(long id) {
        return userrepository.findById(id)
                .filter(user -> "veterinarian".equalsIgnoreCase(user.getRole()));
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
                owner.getRole(),
                owner.getName()
        );
    }

    public UserEntity convertToUserEntity(Seller seller) {
        return new UserEntity(
                seller.getId(),
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

    @Override
    public UserEntity findEntityById(long ownerId) {
        return userrepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("❌ Dueño no encontrado con ID: " + ownerId));
    }
    
    @Override
    public Optional<UserEntity> findVeterinarianById(long id) {
        //System.out.println("🔍 Buscando veterinario con ID: " + id);
        Optional<UserEntity> result = userrepository.findByIdAndRoleIgnoreCase(id, "veterinarian");
       // System.out.println("🔍 Resultado encontrado: " + result.isPresent());
        return result;
    }

    @Override
    public Optional<UserEntity> findOwnerById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("⚠️ El ID del dueño no puede ser nulo.");
        }

        return userrepository.findByIdAndRoleIgnoreCase(id, "OWNER");
    }
    @Override
    public boolean existsById(Long id) {
        return userrepository.existsById(id);
    }

}
