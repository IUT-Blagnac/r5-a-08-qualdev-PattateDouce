package hellocucumber;

import com.github.cukedoctor.Cukedoctor;
import com.github.cukedoctor.api.CukedoctorConverter;
import com.github.cukedoctor.api.DocumentAttributes;
import com.github.cukedoctor.api.model.Feature;
import com.github.cukedoctor.config.GlobalConfig;
import com.github.cukedoctor.parser.FeatureParser;
import com.github.cukedoctor.util.FileUtil;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.Test;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

import java.util.List;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static org.junit.Assert.assertTrue;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("hellocucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/cucumber.json")
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/hellocucumber", glue = "com.hellocucumber.stepdefinitions" )
public class RunCucumberTest {
    @Test
    public void shouldSaveDocumentationIntoDisk() {
        List<String> pathToCucumberJsonFiles = FileUtil.findJsonFiles("target/test-classes/json-output/");
        List<Feature> features = FeatureParser.parse(pathToCucumberJsonFiles);
        DocumentAttributes attrs = GlobalConfig.getInstance().getDocumentAttributes();
        attrs.toc("left").backend("html5")
                .docType("book")
                .icons("font")
                .sourceHighlighter("coderay")
                .docTitle("Documentation Title")
                .sectAnchors(true).sectLink(true);

        CukedoctorConverter converter = Cukedoctor.instance(features, attrs);
        converter.setFilename("target/living_documentation.adoc");

        converter.saveDocumentation();
        assertTrue(FileUtil.loadFile("target/living_documentation.adoc").exists());
    }
}
