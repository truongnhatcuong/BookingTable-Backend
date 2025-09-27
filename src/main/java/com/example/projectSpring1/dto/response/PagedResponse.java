package com.example.projectSpring1.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PagedResponse<T> {
     List<T> content;
     int page;
     int limit;
     long totalRecords;
     int totalPages;
}
