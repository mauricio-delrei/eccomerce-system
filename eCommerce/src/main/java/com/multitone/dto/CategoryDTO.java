package com.multitone.dto;

import com.multitone.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDTO {
    private Long id;
    private String name;



    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
    }
}
