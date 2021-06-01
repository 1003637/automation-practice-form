package components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private SelenideElement dateOfBirthInput = $("#dateOfBirthInput"),
                datepickerYear = $(".react-datepicker__year-select"),
                datepickerMonth = $(".react-datepicker__month-select");

    public void selectBirthday(String date, String month, String year){
        dateOfBirthInput.click();
        datepickerYear.selectOption(year);
        datepickerMonth.selectOption(month);
        $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", date)).click();
    }

}
