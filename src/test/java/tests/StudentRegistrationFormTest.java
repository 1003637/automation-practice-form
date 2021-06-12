package tests;

import com.codeborne.selenide.Configuration;
import components.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.*;
import pages.RegistrationPage;
import com.github.javafaker.Faker;
import utils.RandomUtils;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.*;

public class StudentRegistrationFormTest {
    Logger logger = LoggerFactory.getLogger(StudentRegistrationFormTest.class);
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
     lastName = faker.name().lastName(),
     userEmail = faker.internet().emailAddress(),
     gender = "Male",
     userNumber = faker.phoneNumber().subscriberNumber(10),
     year = getRandomYear(),
     date = getRandomDay(),
     month = getRandomMonth(),
     dateOfBirth = date + " " + month + "," + year,
     hobbies = getRandomHobby(),
     subject = "Maths",
     currentAddress = faker.address().fullAddress(),
     state = "NCR",
     city = "Delhi",
     uploadFile = "auto_test_pic.jpeg";

    @BeforeAll
    static void setUp(){
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void successfulRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage();
        String[] tableLables = {"Student Name", "Student Email", "Gender", "Mobile", "Date of Birth", "Subjects", "Hobbies", "Picture", "Address", "State and City"};
        String[] tableValue = {firstName + " " + lastName, userEmail, gender, userNumber, dateOfBirth, subject, hobbies, uploadFile, currentAddress, state + " " + city};
        Table table = new Table();

        logger.info("Step 1: Fill in \"Student Registration Form\"");
        registrationPage.typeFirstName(firstName);
        registrationPage.typeLastName(lastName);
        registrationPage.typeUserEmail(userEmail);
        registrationPage.selectGender(gender);
        registrationPage.typeUserNumber(userNumber);
        registrationPage.setDate(date, month, year);
        registrationPage.typeSubjectsInput(subject);
        registrationPage.selectHobby(hobbies);
        registrationPage.uploadPicture("/src/test/resources/auto_test_pic.jpeg");
        registrationPage.typeCurrentAddress(currentAddress);
        registrationPage.scrollDown();
        registrationPage.selectState(state);
        registrationPage.selectCity(city);
        registrationPage.submit();

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
