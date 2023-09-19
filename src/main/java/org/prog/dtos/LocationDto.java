package org.prog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto {

    private StreetDto street;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private CoordinatesDto coordinates;
    private TimezoneDto timezone;

}
