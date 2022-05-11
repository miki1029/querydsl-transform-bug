package com.example.querydsltransformbug;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "parent")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    @Id
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Parent parent;
}
