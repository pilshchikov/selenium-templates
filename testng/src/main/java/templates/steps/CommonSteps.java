package templates.steps;


import com.templates.core.Driver;
import lombok.Getter;
import lombok.experimental.Accessors;
import templates.pages.AbstractPage;

public class CommonSteps {

    private Driver driver;

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final AbstractPage page = new AbstractPage(driver);

    public CommonSteps(Driver driver) {
        this.driver = driver;
    }
}
