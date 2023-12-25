package com.epam.esm.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }
}