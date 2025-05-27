/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Converted;

import app.domain.models.MedicationOrderItem;
import app.rest.request.MedicationOrderItemRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MedicationOrderItemRequestConverter {

    public static MedicationOrderItem convertToDomain(MedicationOrderItemRequest request) {
        if (request == null) {
            return null;
        }
        MedicationOrderItem domain = new MedicationOrderItem();
        domain.setMedicationName(request.getMedicationName());
        domain.setDose(request.getDose());
        domain.setQuantity(request.getQuantity());
        domain.setInstructions(request.getInstructions());
        return domain;
    }

    public static List<MedicationOrderItem> convertToDomainList(List<MedicationOrderItemRequest> requests) {
        if (requests == null) {
            return Collections.emptyList();
        }
        return requests.stream()
                .map(MedicationOrderItemRequestConverter::convertToDomain)
                .collect(Collectors.toList());
    }
}
