public class Main {

    public static void main(String[] args) {

        System.out.println("Test");

        TemplateContext templateContext = new TemplateContext();
        templateContext.setBasePackage("my.base.package");

        TemplateWriter templateWriter = new TemplateWriter();
        templateWriter.setTemplateContext(templateContext);
        templateWriter.writeTemplate();

    }

}
