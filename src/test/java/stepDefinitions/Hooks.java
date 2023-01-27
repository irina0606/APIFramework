package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //execute this code only when place id is null
        //write a code that will give you place id

        StepDef sd = new StepDef();
        if(StepDef.placeID == null){
            sd.addPlacePayloadWith("New Place", "German", "Germany");
            sd.userCallsWithHttpRequest("addPlaceAPI", "POST");
            sd.verifyPlace_idCreatedMapsToUsing("New Place", "getPlaceAPI");
        }
    }
}
