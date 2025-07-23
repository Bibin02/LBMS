package com.project.lbms.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @Column(name = "cart_uid")
    private String cartId;
    @Column(name = "is_ordered")
    private boolean isOrdered; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uid")
    private Users cartUser;

    @OneToMany(mappedBy = "bookCartIdObject")
    private Set<CartBook> cartBooks;
}
