/**
 * Author : Rohan Shirke
 */
$(function () {
    // VARIABLES =============================================================
    var TOKEN_KEY = "jwtToken"


    	
    var $login = $("#login");
    var $postlogin = $("#postlogin").hide();
    var $bodyx = $("#bodyx");
    var $body1 = $("#body1").hide();
    var $footer = $("#footer");
    var $contentpage1 = $("#contentpage1");
    var $contentpage2 = $("#contentpage2");
    var $contentpage3 = $("#contentpage3");
    
    var $table = $("#table1");
    
    // FUNCTIONS =============================================================
    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }

    function removeJwtToken() {
        localStorage.removeItem(TOKEN_KEY);
    }

    function doLogin(loginData) {
        $.ajax({
            url: "/auth",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
            	//user successfully logged in !!
                setJwtToken(data.token);
                $bodyx.hide();
                $body1.show();
                $login.hide();
                $postlogin.show();
                $footer.hide();
                $contentpage2.hide();
                $contentpage3.hide();
               
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }    
    
    
    function doRegister(registerData) {
        $.ajax({
            url: "/register",
            type: "POST",
            data: JSON.stringify(registerData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
            	//user successfully logged in !!
                setJwtToken(data.token);
                $bodyx.hide();
                $body1.show();
                $login.hide();
                $postlogin.show();
                $footer.hide();
                $contentpage1.show();
                $contentpage2.hide();
                $contentpage3.hide();
               
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }    
    
 function bookBlood(bloodData) {
    	
    	console.log("The id is " + bloodData);
       /* $.ajax({
            url: "/bookblood",
            type: "POST",
            data: JSON.stringify(searchData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
            //confirmed the transaction was complete  	
            	alert("The requested blood got booked !!!")              
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
        $contentpage1.hide();
        $contentpage2.show();
        */
    }
    
    function searchBanksfunc(searchData) {
        $.ajax({
            url: "/bloodbanks/getallbb1",
            type: "POST",
            data: JSON.stringify(searchData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
            	           	
            	if(data){
                    var len = data.length;
                    console.log(len);
                    var txt = "";
                    if(len > 0){
                        for(var i=0;i<len;i++){
                        	
                            if(data[i].bloodBankName && data[i].aPlusGroupQty){
                                txt += "<tr><td class=\"bbid\">"+data[i].id+"</td><td class=\"bbname\">"+data[i].bloodBankName+"</td><td class=\"Aqty\">"+data[i].aPlusGroupQty+"</td><td><button id=\"sendrow\">Book</button> </td></tr>";
                                console.log(txt);
                            }
                        }
                        if(txt != ""){
                            $("#table").append(txt).removeClass("hidden");
                        }
                    }
                
            	
            	// displaying map 
            	var map = new google.maps.Map(document.getElementById('map'), {
            	      zoom: 10,
            	      center: new google.maps.LatLng(41.8754, -87.6248),
            	      mapTypeId: google.maps.MapTypeId.ROADMAP
            	    });

            	    var infowindow = new google.maps.InfoWindow();

            	    var marker, i;

            	    for (i = 0; i < len; i++) {  
            	      marker = new google.maps.Marker({
            	        position: new google.maps.LatLng(data[i].geoX, data[i].geoY),
            	        map: map
            	      });

            	      google.maps.event.addListener(marker, 'click', (function(marker, i) {
            	        return function() {
            	          infowindow.setContent(locations[i][0]);
            	          infowindow.open(map, marker);
            	        }
            	      })(marker, i));
            	    }
            	
                
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }

    
       
    function doLogout() {
    	removeJwtToken();
    	$login.show();
        $postlogin.hide();
        $bodyx.show();
        $body1.hide();
        $footer.show();
        
    }

    function createAuthorizationTokenHeader() {
        var token = getJwtToken();
        if (token) {
            return {"Authorization": token};
        } else {
            return {};
        }
    }

    function showUserInformation() {
        $.ajax({
            url: "/user",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                var $userInfoBody = $userInfo.find("#userInfoBody");

                $userInfoBody.append($("<div>").text("Username: " + data.username));
                $userInfoBody.append($("<div>").text("Email: " + data.email));

                var $authorityList = $("<ul>");
                data.authorities.forEach(function (authorityItem) {
                    $authorityList.append($("<li>").text(authorityItem.authority));
                });
                var $authorities = $("<div>").text("Authorities:");
                $authorities.append($authorityList);

                $userInfoBody.append($authorities);
                $userInfo.show();
            }
        });
    }

    function showTokenInformation() {
        $loginText
            .text("Token: " + getJwtToken())
            .attr("title", "Token: " + getJwtToken())
            .show();
    }

    function showResponse(statusCode, message) {
        $response
            .empty()
            .text("status code: " + statusCode + "\n-------------------------\n" + message);
    }

    // REGISTER EVENT LISTENERS =============================================================
    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });
    
    
    
    
    $("#registerForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var registerData = {
            username: $form.find('input[name="username1"]').val(),
            lastname: $form.find('input[name="lastname1"]').val(),
            email: $form.find('input[name="email1"]').val(),
            password: $form.find('input[name="password1"]').val()
        };
        
        console.log(registerData);
        
        console.log("going to send the data to server");

        doRegister(registerData);
    });
    
    $("#searchBanks").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var searchData = {
            bloodGroup: $form.find('input[name="bgroup"]').val(),
            bloodQuantity: $form.find('input[name="bquantity"]').val()
        };

        searchBanksfunc(searchData);
    });
    
    $('#sendrow').click(function(){
    	
        /*var qty = $(this).parent().siblings('.Aqty').text();
        var bbname = $(this).parent().siblings('.bbname').text();
        var bbid = $(this).parent().siblings('.bbid').text();
 
        var bloodData = {
                Qty: qty,
                Bbname: bbname,
                Bbid: bbid
            };
        
        bookblood(bloodData);*/
    	
    	console.log("Book button was pressed");
    });
    

    $("#logout").click(doLogout);

    $("#exampleServiceBtn").click(function () {
        $.ajax({
            url: "/persons",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            	//showResponse(jqXHR.status, data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#adminServiceBtn").click(function () {
        $.ajax({
            url: "/protected",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

     // INITIAL CALLS =============================================================
    if (getJwtToken()) {
        $login.show();
       
    }
});