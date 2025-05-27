// src/main/java/app/Converted/MedicationOrderItemConverter.java
package app.Converted;

import app.Entities.MedicationOrderItemEntity;
import app.domain.models.MedicationOrderItem;
import java.util.List;
import java.util.stream.Collectors;

public class MedicationOrderItemConverter {

    public static MedicationOrderItem convertToDomain(MedicationOrderItemEntity entity) {
        if (entity == null) {
            return null;
        }
        MedicationOrderItem domain = new MedicationOrderItem();
        domain.setMedicationName(entity.getMedicationName());
        domain.setDose(entity.getDose());
        domain.setQuantity(entity.getQuantity());
        domain.setInstructions(entity.getInstructions());
        return domain;
    }

    public static MedicationOrderItemEntity convertToEntity(MedicationOrderItem domain) {
        if (domain == null) {
            return null;
        }
        MedicationOrderItemEntity entity = new MedicationOrderItemEntity();
        entity.setMedicationName(domain.getMedicationName());
        entity.setDose(domain.getDose());
        entity.setQuantity(domain.getQuantity());
        entity.setInstructions(domain.getInstructions());
        return entity;
    }

    // 🚀 Agrega estos dos:
    public static List<MedicationOrderItem> convertToDomainList(List<MedicationOrderItemEntity> entities) {
        return entities.stream()
                .map(MedicationOrderItemConverter::convertToDomain)
                .collect(Collectors.toList());
    }

    public static List<MedicationOrderItemEntity> convertToEntityList(List<MedicationOrderItem> domains) {
        return domains.stream()
                .map(MedicationOrderItemConverter::convertToEntity)
                .collect(Collectors.toList());
    }
}
