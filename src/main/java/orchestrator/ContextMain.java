package orchestrator;

import lombok.Data;

@Data
public class ContextMain {

    private String basePackage;
    private String group;
    private String version;
    private String targetFolder;

}
