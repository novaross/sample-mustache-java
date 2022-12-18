package com.rs.modbase;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.rs.helper.TemplateWriter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Data
@Slf4j
public class ModBase {
    private final static String TEMPLATE_FOLDER = "templateRoot";
    private ContextModBase contextModBase;

    public void process() {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> templateContextMap = oMapper.convertValue(contextModBase, Map.class);

        TemplateWriter templateWriter = new TemplateWriter();
        templateWriter.setTemplateFolder(TEMPLATE_FOLDER);
        templateWriter.setTargetFolder(contextModBase.getTargetFolder());
        templateWriter.setTemplateContextMap(templateContextMap);
        templateWriter.process();
    }

}
