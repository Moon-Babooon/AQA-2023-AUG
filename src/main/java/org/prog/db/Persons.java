package org.prog.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.prog.dto.UserDto;

import javax.persistence.*;

@Entity
@Table(name = "Persons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persons {

    @Id
    @Column(name = "PersonID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "Title")
    private String title;

    @Column(name = "Gender")
    private String gender;

    public static Persons fromDto(UserDto dto) {
        return Persons.builder()
                .lastName(dto.getName().getLast())
                .firstName(dto.getName().getFirst())
                .gender(dto.getGender())
                .title(dto.getName().getTitle())
                .build();
    }
}