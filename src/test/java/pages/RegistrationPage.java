package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import java.io.File;
import components.CalendarComponent;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationPage {
    SelenideElement firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            genterWrapper = $("#genterWrapper"),
            userNumber = $("#userNumber"),

            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            city = $("#city"),
            stateCityWrapper = $("#stateCity-wrapper"),
            submit = $("#submit"),
            userForm = $("#userForm");

    CalendarComponent calendarComponent = new CalendarComponent();

    public void typeFirstName(String firstNameText){
        firstName.val(firstNameText);
    }

    public void typeLastName(String lastNameText){
        lastName.val(lastNameText);
    }

    public void typeUserEmail(String mail){
        userEmail.val(mail);
    }

    public void selectGender(String gender){
        genterWrapper.$(byText(gender)).click();
    }

    public void typeUserNumber(String number){
        userNumber.val(number);
    }

    public void setDate(String date, String month, String year){
        calendarComponent.selectBirthday(date, month, year);
    }

    public void typeSubjectsInput(String subject){
        subjectsInput.val(subject).pressEnter();
    }

    public void selectHobby(String hobby){
        hobbiesWrapper.$(byText(hobby)).click();
    }

    public void uploadPicture(String filePath){
        uploadPicture.uploadFile(new File(filePath));
    }

    public void typeCurrentAddress(String address){
        currentAddress.val(address);
    }

    public void selectState(String stateText){
        state.click();
        stateCityWrapper.$(byText(stateText)).click();
    }

    public void selectCity(String cityText){
        city.click();
        stateCityWrapper.$(byText(cityText)).click();
    }

    public void submit(){
        submit.click();
    }

    public void scrollDown(){
        userForm.scrollTo();
    }

    public void checkResults(String firstName,
                             String lastName,
                             String email,
                             String gender,
                             String mobile,
                             String dayOfBirth,
                             String monthOfBirth,
                             String yearOfBirth,
                             String subject,
                             String hobby,
                             String picture,
                             String currentAddress,
                             String state,
                             String city)
    {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $$("tbody tr").filter(text("Student name")).shouldHave(texts(firstName + " " + lastName));
        $$("tbody tr").filter(text("Student Email")).shouldHave(texts(email));
        $$("tbody tr").filter(text("Gender")).shouldHave(texts(gender));
        $$("tbody tr").filter(text("Mobile")).shouldHave(texts(mobile));
        $$("tbody tr").filter(text("Date of Birth")).shouldHave(texts(dayOfBirth+" "+monthOfBirth+","+yearOfBirth));
        $$("tbody tr").filter(text("Subjects")).shouldHave(texts(subject));
        $$("tbody tr").filter(text("Hobbies")).shouldHave(texts(hobby));
        $$("tbody tr").filter(text("Picture")).shouldHave(texts(picture));
        $$("tbody tr").filter(text("Address")).shouldHave(texts(currentAddress));
        $$("tbody tr").filter(text("State and City")).shouldHave(texts(state+" "+city));
    }
}
