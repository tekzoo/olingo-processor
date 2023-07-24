package com.tekzoo.odata.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "Order")
@Table(name= "VOrder", schema="itel")
public class Order {
    @Id
    private String id;
    private String fullName;
    private String contactPhone;
    private Date createDate;
    private Long totalPrice;
    private int deliveryType;
    private String createFrom;
    private String shippingPartner;
    private String paymentChannel;

    public Order(String id, String fullName, String contactPhone, Date createDate, Long totalPrice, int deliveryType, String createFrom, String shippingPartner, String paymentChannel) {
        this.id = id;
        this.fullName = fullName;
        this.contactPhone = contactPhone;
        this.createDate = createDate;
        this.totalPrice = totalPrice;
        this.deliveryType = deliveryType;
        this.createFrom = createFrom;
        this.shippingPartner = shippingPartner;
        this.paymentChannel = paymentChannel;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getCreateFrom() {
        return createFrom;
    }

    public void setCreateFrom(String createFrom) {
        this.createFrom = createFrom;
    }

    public String getShippingPartner() {
        return shippingPartner;
    }

    public void setShippingPartner(String shippingPartner) {
        this.shippingPartner = shippingPartner;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }
}