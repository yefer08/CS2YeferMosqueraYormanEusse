/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;




import app.Entities.InvoiceEntity;
import app.domain.models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InvoiceServiceRepository extends JpaRepository<InvoiceEntity, String> {

    Optional<InvoiceEntity> findById(String id); 

    public void save(Invoices invoice);
}
