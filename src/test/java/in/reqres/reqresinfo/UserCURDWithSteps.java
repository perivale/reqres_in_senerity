package in.reqres.reqresinfo;

import in.reqres.constants.EndPoints;
import in.reqres.testbase.TestBase;
import in.reqres.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class UserCURDWithSteps extends TestBase {
    static String email = "PrimUser" + TestUtils.getRandomValue();
    static String password = "PrimeUser" + TestUtils.getRandomValue();

    static int userId;

    @Steps
    UserSteps userSteps;


    @Title("This will create a new student")
    @Test
    public void test001() {
        ValidatableResponse response=userSteps.createusers(email,password);
         response.log().all();

    }


    @Title("Verify if the servicesdata was added to the application")
    @Test
    public void test002() {
        ValidatableResponse response=userSteps.getuserinfobyid(userId);
        response.body("email",equalTo(email));

    }
    @Title("update the user information and verify the update info ")
    @Test
    public void test003 (){
        email=email+"_u03";
        ValidatableResponse response=userSteps.updateTheUserid(userId,email);
        response.log().all().statusCode(200);
        ValidatableResponse response1=userSteps.getuserinfobyid(userId);
        response1.body("email",equalTo(email));

    }
    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004() {

        userSteps.deleteuserId(userId).statusCode(200);
        userSteps.verifyuserid(userId).statusCode(404);
    }
}
