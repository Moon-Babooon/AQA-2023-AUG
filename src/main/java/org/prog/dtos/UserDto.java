package org.prog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.prog.dtos.LocationDto;
import org.prog.dtos.NameDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String gender;
    private NameDto name;
    private String nat;
    private LocationDto location;

}
