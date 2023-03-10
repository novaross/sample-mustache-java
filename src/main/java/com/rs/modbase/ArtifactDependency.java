package com.rs.modbase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArtifactDependency {

    private String annotation;
    private String group;
    private String name;
    private String version;

}
