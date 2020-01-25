public class Main {

    public static void main(String[] args) {

        TemplateContext templateContext = new TemplateContext();
        templateContext.setBasePackage("my.base.package");

        TemplateWriter templateWriter = new TemplateWriter();
        templateWriter.setTemplateFolder("templateRoot");
        templateWriter.setOutputFolder("c:/temp");
        templateWriter.setTemplateContext(templateContext);
        templateWriter.process();

    }

}
