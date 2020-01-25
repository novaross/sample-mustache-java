package modbase;

import lombok.Data;

import java.util.List;

@Data
public class ContextModBase {

    private String version;
    private String basePackage;
    private String group;
    private String targetFolder;
    private List<ArtifactDependency> dependencies;

}
