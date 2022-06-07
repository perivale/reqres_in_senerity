package in.reqres.reqresinfo;

import in.reqres.constants.EndPoints;
import in.reqres.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;

public class UserSteps {
    @Step("Creating student with email : {0}, password: {1}, ")
    public ValidatableResponse createusers(String email, String password) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(email);
        userPojo.setPassword(password);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post()
                .then();

    }

    @Step("Creating student with email : {0} ")
    public ValidatableResponse getuserinfobyid(int id) {

        return SerenityRest.given()
                //  .contentType(ContentType.JSON)
                .pathParam("/users/2", id)
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then();
    }

    @Step("Creating product with Name : {0}, type: {1}, price: {2}, shiping: {3}  upc: {4}, description :{5},manufacturer:{6}, model:{7}, url :{8},image :{9} ")

    public ValidatableResponse updateTheUserid(int id, String email) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(email);


        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("users/2", id)
                .body(userPojo)
                .when()
                .patch(EndPoints.UPDATE_STUDENT_BY_ID)
                .then();
    }

    @Step("Delete id:{0}")
    public ValidatableResponse deleteuserId(int id) {
        return SerenityRest.given().log().all()
                .pathParam("/users/2", id)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();
    }

    @Step("verify that user id is delete:{0}")
    public ValidatableResponse verifyuserid(int id) {
        return SerenityRest.given().log().all()
                .pathParam("users/2", id)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then();

    }
}