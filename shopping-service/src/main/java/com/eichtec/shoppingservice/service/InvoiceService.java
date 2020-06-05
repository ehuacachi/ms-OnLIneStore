package com.eichtec.shoppingservice.service;

import com.eichtec.shoppingservice.entity.Invoice;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface InvoiceService {
    public List<Invoice> findInvoiceAll();
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);
    public Invoice getInvoice(Long id);

}
