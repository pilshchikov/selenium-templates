package templates.pages.yandex;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author Stepan Pilshchikov
 * @created 22.07.2017
 */
@Name("Result item")
@FindBy(css = ".serp-item")
public class ResultItem extends HtmlElement {
}
