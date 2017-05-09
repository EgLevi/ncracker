<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
  <head>
    <!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <!--  <meta Content-type= "text/html"; charset=utf-8> -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">  -->
    <title>Bootstrap 101 Template</title>

    <link rel="stylesheet" type="text/css" href="resources/css/affablebean.css"> 
    <style>
       .select-tag{
          width: 800px;
      }
    </style>
    <!-- Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap-chosen.css" rel="stylesheet">

    <link href="resources/css/select2.min.css" rel="stylesheet">

  </head>
  <body style="background-attachment:fixed" topmargin="10">

  <header>
            <div>
                <!--<div class="row"> -->
                    <div class="col-md-12">
                      <!-- header Nav Start -->
                        <nav class="navbar navbar-default"> <!-- navbar-default navbar-static-top-->
                            <div class="container-fluid">
                              <!-- Brand and toggle get grouped for better mobile display -->
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span> 
                                        <span class="icon-bar"></span>
                                    </button>
                                    <a class="navbar-default" href="index.html">
                                      <img src="resources/images/logo4.png" alt="Logo" height='50' align='top'>
                                    </a>
                                </div>
                                <!-- Collect the nav links, forms, and other content for toggling -->
                                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                    <ul class="nav navbar-nav navbar-right">
                                      <li><a href="index.html">Главная</a></li>
                                      <li><a href="#">Поддержка</a></li>
                                      <li><a href="#">О нас</a></li>
                                      <li><a href="contact.html">Контакты</a></li>
                                    </ul>
                                </div><!-- /.navbar-collapse -->
                              </div><!-- /.container-fluid -->
                          </nav>
                      <!--</div> -->
                  </div>
              </div>
        </header><!-- header close --> 

    <div class="container">
        <div class="row">
        
          <div class="col-md-6">    
            <div class="about"> 
              <div class="left-group">
                  
                  <div class="list-group" >
                  	<c:forEach items="${CategoryList}" var="category" varStatus="status">
	                    <a href="#" value="${category}" class="list-group-item">${category}</a>
                     </c:forEach>
                  </div>

              </div>
            </div>
          </div>

         <div class="col-md-6">          
            <form>
                <select id="select" multiple class="chosen-select" tabindex="-1">      	
                </select>
            </form>
          </div>
        </div>
        <div class="row">
          <div class="col-md-8">
            <select multiple class="select-tag" tabindex="-1" aria-hidden="true">
            </select>
          </div>  
        </div>         

        
    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="resources/js/chosen.jquery.js"></script>
    <script src="resources/js/select2.min.js"></script>
    <script src="resources/js/jquery.json.js"></script>
        <script>
        
                $(document).ready(function() {
                  $('.chosen-select').chosen();                  
                  $('.select-tag').select2({
                    tags: true
                  });
                  
                  $('.list-group-item').on('click', function() {
                	  /*
                	  	Здесь очистищаю select
                	  */
                	  var action_list = document.getElementById("select");
                      i = action_list.options.length;
  					  while (i--)
  					  {
  					    action_list.remove(i);
  					    $('#select').trigger('chosen:updated');					    
  					  }
                	  //var d = {foo: "fooValue"};
                	  var key = {name: $(this).text()};	
	  					$.ajax({
	  						type:"POST",
	  						url:"ajaxtest.html",
	  						data:JSON.stringify(key),
	  						contentType: "application/json",
	  						success: function(data){	  	
	  							//получаю объект JavaScript
	  							var returnedData = JSON.parse(data);	
	  							//получаю массив из объекта
	  								var array = $.map(returnedData, function(value, index) {
	  								    return [value];
	  								});
	  							for(var i = 0; i < array.length; i++){
	  								$('#select').append('<option value="'+array[i]+'">'+array[i]+'</option>');
		      	  					$('#select').trigger('chosen:updated');
	  							}
	  						},
	  						error : function(jqXHR, textStatus, errorThrown) {
	  			                alert("Error! "+textStatus+" "+" "+jqXHR);	  			              
	  			              	console.log(jqXHR);
	  			            	console.log(textStatus);
	  			            	console.log(errorThrown);	  			            	
	  			            }
	  					});
	                  });             	
            });           
        </script>
  </body>
</html>