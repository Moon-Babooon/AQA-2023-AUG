package org.prog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.prog.db.Persons;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String gender;
    private NameDto name;
    private String nat;

    public static UserDto fromDB(Persons persons){
        return UserDto.builder()
                .gender(persons.getGender())
                .name(NameDto.builder()
                        .first(persons.getFirstName())
                        .last(persons.getLastName())
                        .title(persons.getTitle())
                        .build())
                .build();
    }
}
