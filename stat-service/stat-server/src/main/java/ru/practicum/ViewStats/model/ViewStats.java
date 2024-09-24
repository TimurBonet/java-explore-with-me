package ru.practicum.ViewStats.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ViewStats {
    @NotBlank
    @Size(min = 1, max = 70)
    private String app;
    @NotBlank
    @Size(min = 1, max = 255)
    private String uri;
    @NotNull
    private Long hits;
}