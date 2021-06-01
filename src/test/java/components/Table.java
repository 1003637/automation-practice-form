package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$;

public class Table {

    private SelenideElement self = $(".table");
    private ElementsCollection rows = self.$("tbody").$$("tr");

    public ElementsCollection getTableRows() {
        return rows.shouldBe(sizeGreaterThan(0));
    }

    public SelenideElement getRow(int indexOfRow) {
        return getTableRows().get(indexOfRow);
    }

    public ElementsCollection getCellsForRow(int rowPosition) {
        return rows.shouldBe(sizeGreaterThanOrEqual(rowPosition + 1))
                .get(rowPosition).$$("td");
    }

    public SelenideElement getFirstRow() {
        return getTableRows().first();
    }
}
