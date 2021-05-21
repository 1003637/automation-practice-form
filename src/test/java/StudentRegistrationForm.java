import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.*;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationForm {
    Logger logger = LoggerFactory.getLogger(StudentRegistrationForm.class);
    String firstName = "AutoUserFirstName";
    String lastName = "AutoUserLastName";
    String userEmail = "auto_email@mail.com";
    String gender = "Male";
    String userNumber = "3809399910";
    String year = "1980";
    String dateOfBirth = "18 April,1980";
    String hobbies = "Sports, Music";
    String subject = "Maths";
    String currentAddress = "West Road 12";
    String state = "NCR";
    String city = "Delhi";
    String uploadFile = "auto_test.jpeg";

    @BeforeAll
    static void setUp(){
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void fillInStudentFormTest(){
        String[] tableLables = {"Student Name", "Student Email", "Gender", "Mobile", "Date of Birth", "Subjects", "Hobbies", "Picture", "Address", "State and City"};
        String[] tableValue = {firstName + " " + lastName, userEmail, gender, userNumber, dateOfBirth, subject, hobbies, uploadFile, currentAddress, state + " " + city};
        Table table = new Table();

        logger.info("Step 1: Fill in \"Student Registration Form\"");

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(userEmail);
        $("[for=gender-radio-1]").click();
        $("[id=userNumber]").setValue(userNumber);
        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__year-select").click();

        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption(3);
        $(".react-datepicker__month-select").click();

        $(".react-datepicker__day--018").click();

        $("[id=subjectsInput]").setValue(subject);
        $("[id=subjectsInput]").pressEnter();

        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-3]").click();
        $("[id=uploadPicture]").uploadFile(new File("/src/test/resources/auto_test.jpeg"));
        $("[id=currentAddress]").setValue(currentAddress);

        $("[id=userForm]").scrollTo();

        $("[id=react-select-3-input]").setValue(state);
        $("[id=react-select-3-input]").pressEnter();
        $("[id=react-select-4-input]").setValue(city);
        $("[id=react-select-4-input]").pressEnter();

        $("#submit").click();

        logger.info("Step 2: Check results of filling in of the registration form");
        logger.info("First way of checking all rows in the table");
        $$(".table tbody tr").shouldHave(exactTexts(
                tableLables[0] + " " + firstName + " " + lastName,
                tableLables[1] + " " + userEmail,
                tableLables[2] + " " + gender,
                tableLables[3] + " " + userNumber,
                tableLables[4] + " " + dateOfBirth,
                tableLables[5] + " " + subject,
                tableLables[6] + " " + hobbies,
                tableLables[7] + " " + uploadFile,
                tableLables[8] + " " + currentAddress,
                tableLables[9] + " " + state + " " + city));

        logger.info("Second way of checking all rows in the table");
        int row = 0;
        for(String value: tableValue){
            table.getRow(row).shouldHave(text(value));
            row++;
        }


    }
}
