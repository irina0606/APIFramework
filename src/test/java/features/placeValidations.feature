Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if Place has been successfully added using AddPlaceAPI request

    Given Add place Payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" with "Post" http request
    Then User gets response status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name   | language    | address
      | AAA    | English     | Florida
      | BBB    | Spanish     | Mexico
      | CCC    | Mandarin    | China

  @DeletePlace
  Scenario: Verify Delete Place functionality

    Given Delete Place Payload
    When User calls "deletePlaceAPI" with "Delete" http request
    Then User gets response status code 200
    And "status" in response body is "OK"



