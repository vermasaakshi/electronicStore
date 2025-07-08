package com.pratik.electronic.store.ElectronicStore.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {

        private String imageName;
        private String message;
        private Boolean success;
        private HttpStatus status;

}
