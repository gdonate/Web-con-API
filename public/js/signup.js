//Global uri
var BASE_URI = "http://localhost:8080/dsaApp/";

//Calls when the document is ready
$(document).ready(function(){
 //Code if the user clicks sign up button
 $("#signupbutton").click(function(){
    var username = $(".username").val();
    console.log(username);
    var password = $(".password").val();
    console.log(password);
    console.log("Estoy en signup script");
    //Create the object that we want to pass, which is user
    var user = {
      "username": username,
      "password": password,
    };
    console.log(user);
    $.ajax({
      headers: { 'content-type': 'application/json',"x-kii-appid": "XXXXX","x-kii-appkey":"XXXXX" },
      type: 'POST',
      url:  BASE_URI.concat("user/registerUser"),
      data: JSON.stringify(user),
      dataType: 'json',
      success: function(data) {
          console.log("Sign up successfully");
          console.log(data);
          alert("User successfully created");
          window.open("http://localhost:8080","_self");
      },
      error: function(error){
        if(error.status==500){
          alert("Username already exists. Use another username");
        }
      }
  });
});
});