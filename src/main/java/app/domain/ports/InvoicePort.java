/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package veterinarianapp.port;

import Models.Invoice;
import java.util.List;

/**
 *
 * @author Yorman
 */
public interface InvoicePort {
        void generateInvoice(Invoice invoice);
    List<Invoice> listInvoice();

}
