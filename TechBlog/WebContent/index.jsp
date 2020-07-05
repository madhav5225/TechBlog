<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>			
    <%@page errorPage="ErrorPage.jsp" %> 

  <%@ page import="com.tech.blog.entities.*" %>
  <%@ page import="com.tech.blog.dao.*" %>
  <%@ page import="com.tech.blog.helper.ConnectionProvider" %>
  <%@ page import="java.util.Vector" %>
  <%@ page import="java.text.*" %>
 
   
   
   
 <% User user=null;
if(session.getAttribute("user")!=null)
{
 user=(User)session.getAttribute("user");
}
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0); // Proxies.
%>
<!-- Retrieving Message from Message Class -->
<%
MessageClass msg=(MessageClass)session.getAttribute("message");
%>
<!-- Getting all categories -->
   <%
   PostDao postd = new PostDao(ConnectionProvider.getConnection());
   Vector<Categories> cat = postd.getAllCategories();
   %> 
   <!-- Retrieving All the posts -->
   <%
  
   Vector<Posts> posts;
   int catid=0;
   if(session.getAttribute("catid")!=null)
   {
	  catid=(int) session.getAttribute("catid");
   }
   if(catid==0)
   {
	   posts= postd.getAllPosts();
	      
   }
   else
   {
   posts= postd.getAllPosts(catid);
   }
   %>                       
<!DOCTYPE html>
<html>    
<head>
<meta charset="ISO-8859-1">
<!-- CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <style>
            .primary-background{
             background-color: #1de9b6;
             color:black!important;
            
            }
             .primary-background2{
             background-color: #e65100 ;
             color:black!important;
            
            }
            .upvote-background{
            
clip-path: polygon(50% 0%, 0% 100%, 100% 100%);
}
            .downvote-background{
 clip-path: polygon(50% 100%, 0 0, 100% 0);
 
 }
   </style>
   <style>
#catForm {
  border: 1px ;
  margin-top: 5px;
  margin-bottom: 0px;
  
}
</style>
<title>Insert title here</title>
</head>
<body>
<!-- For Profile Photo -->
<%String path="";
                        if(user!=null)
                        {
                        if(user.getProfile().equals("default.png"))
                        	path="default.png";
                        else
                        	path=user.getId()+ user.getProfile();
                        }
                        %>

<!--  navbar -->
  
<nav class="navbar navbar-expand-lg navbar-dark primary-background2">
  <a class="navbar-brand" href="index.jsp"><span class="fa fa-bank" ></span>TechBlog</a>
  <button class="navbar-toggler" type="button" 
  data-toggle="collapse" 
  data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
   
      <li class="nav-item">
        <a class="nav-link" href="#"><span class="fa fa-address-book"></span> Contact</a>
      </li>
      
      <li class="nav-item">
             <%if(user!=null)
            	 {%>
             <a class="nav-link" href="#" 
             data-toggle="modal" 
             data-target="#add-post-modal" > <span class="	fa fa-asterisk"></span> Do Post
             </a>
             <% }else
             {%>
        	 <a class="nav-link" href="LoginPage.jsp"  >
                      <span class="	fa fa-asterisk"></span> Login
                     </a>
         <% 
         }
             %>
                    </li>
    </ul>
   

                <ul class="navbar-nav mr-right">
                     <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-o	utline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
                    <%if(user!=null)
                    	{%>
                    <li class="nav-item">
                        <a class="nav-link" href="#!" data-toggle="modal" data-target="#profile-modal"> 
                        <img src="img/pics/<%out.print(path);%>" class="img-fluid" style="border-radius:50%;max-width: 50px;max-height:20px;" >
                         &nbsp; <%out.print(user.getName());%> </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LogoutPage" > <span class="fa fa-user-plus "></span> Logout</a>
                    </li>
                     <%}
                    else
                    {%>
            <li class="nav-item">
                        <a class="nav-link" href="RegisterPage.jsp" > <span class="fa fa-user-plus "></span>SignUp</a>
                    </li>
    <%} %>
                    
                    
                </ul>
  </div>
</nav>
<!-- End Of Navbar -->

<!-- Displaying Message -->

<%if(msg!=null)
{
	%>
	<div id="MessageDiv" class="<%=msg.getCssClass() %> ">
    <%=msg.getContent() %>
    <button onclick="removeMessage()" type="button" class="close" data-dismiss="alert">&times;</button>
</div>
	<script>
function removeMessage() {
	</script>
<%	session.removeAttribute("message");
 %>
<script>
 
	
}
</script>
	
	<% 
}
	%>



    <!--profile modal-->
<%if(user!=null){ %>


        <!-- Modal -->
        <div class="modal fade" id="profile-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header primary-background text-white text-center">
                        <h5 class="modal-title" id="exampleModalLabel"> TechBlog </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container text-center">
                        
                            <img src="img/pics/<%=""+path%>" class="img-fluid" style="max-width: 150px;max-height:50px;" >
                            <br>
                            <h5 class="modal-title mt-3" id="exampleModalLabel"> <%= user.getName()%> </h5>
                            <!--//details-->

                            <div id="profile-details">
                                <table class="table">

                                    <tbody>
                                        <tr>
                                            <th scope="row"> ID :</th>
                                            <td> <%= user.getId()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row"> Email : </th>
                                            <td><%= user.getEmail()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Gender :</th>
                                            <td><%= user.getGender()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Status :</th>
                                            <td><%= user.getAbout()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Registered on :</th>
                                            <td><%= user.getDate().toString()%></td>

                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <!--profile edit-->

                            <div id="profile-edit" style="display: none;">
                                <h3 class="mt-2">Please Edit Carefully</h3>
                                <form action="EditServlet" method="post" enctype="multipart/form-data">
                                    <table class="table">
                                        <tr>
                                            <td>ID :</td>
                                            <td><%= user.getId()%></td>
                                        </tr>
                                        <tr>
                                            <td>Email :</td>
                                            <td> <%= user.getEmail()%> </td>
                                        </tr>
                                        <tr>
                                            <td>Name :</td>
                                            <td> <input type="text" class="form-control" name="user_name" value="<%= user.getName()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Password :</td>
                                            <td> <input type="password" class="form-control" name="user_password" value="<%= user.getPassword()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Gender :</td>
                                            <td> <%= user.getGender().toUpperCase()%> </td>
                                        </tr>
                                        <tr>
                                            <td>About  :</td>
                                            <td>
                                                <textarea rows="3" class="form-control" name="user_about" ><%= user.getAbout()%>
                                                </textarea>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>New Profile:</td>
                                            <td>
                                                <input type="file" name="user_image" class="form-control" >
                                            </td>
                                        </tr>

                                    </table>

                                    <div class="container">
                                        <button type="submit" class="btn btn-outline-primary">Save</button>
                                    </div>

                                </form>    

                            </div>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button  id="edit-profile-button" type="button" class="btn btn-primary">EDIT</button>
                    </div>
                </div>
            </div>
        </div>


        <!--end of profile modal-->
      <!--add post modal-->

           <div class="modal fade" id="add-post-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Provide the post details..</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <form id="add-post-form" action="AddPostServlet" method="post" enctype="multipart/form-data">

                            <div class="form-group">
                                <select class="form-control" name="post_cid">
                                    <option selected disabled>---Select Category---</option>

                                    <%
                                   
                                         for (Categories c : cat) {
                                    %>
                                    <option value="<%=c.getCid()%>"><%=c.getCname()%></option>

                                    <%
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="form-group">
                                <input name="post_title" type="text" placeholder="Enter post Title" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <textarea name="post_content" class="form-control" style="height: 200px;" placeholder="Enter your content"></textarea>
                            </div>
                            <div class="form-group">
                                <label>Select your pic..</label>
                                <br>
                                <input class="form-control" type="file" name="post_image"  >
                            </div>

                            <div class="container text-center">
                                <button type="submit" class="btn btn-outline-primary">Post </button>
                            </div>

                        </form>


                    </div>

                </div>
            </div>
        </div>


        <!--END add post modal-->
           <%} %>
           <!--main content of body-->

        
            <div class="container">
                <div class="row mt-4">
                    <!--first col-->
                    <div class="col-md-4">
                        <!--categories-->
                        <div class="list-group">
                             <form id="catForm" action="LoadPage" method="post">
                              <input type="hidden" name="catid" value="0">
                              <%if(catid==0) 
                              {%>
                              <input type="submit" class="btn btn-info btn-block" value=" All Posts" >
                               <%} 
                         else 
                              {%>
                              <input type="submit" class="btn btn-light btn-block" value=" All Posts" >
                               <%} %>
                            </form>
                            <%
                                for (Categories cc : cat) {
                            %>
                            <form id="catForm" action="LoadPage" method="post">
                              <input type="hidden" name="catid" value="<%=cc.getCid()%>">
                             <%if(catid==cc.getCid()) 
                              {%>
                              <input type="submit" class="btn btn-info btn-block" value="<%= cc.getCname()%>">
                               <%} 
                             else{%>
                              <input type="submit" class="btn btn-light	 btn-block" value="<%= cc.getCname()%>">
                               <%} %>  
                       
                            </form>
  
 
                            <%                                  
                            }
                            %>
                        </div>

                    </div>

 <!--second col-->
    
                    <div class="col-md-8" >
    
                        <!--posts-->
                      
                        <div class="container-fluid" id="post-container">

   <div class="container">

            <div class="row my-4">
<% 
                for (Posts post : posts) {
    %>

    <div class="col-md-8 mt-2">
        <div class="card">
        <%if( !post.getPimage().equals(""))
        {
        %>
            <img class="card-img-top" src="img/postPics/<%= post.getPid()%><%= post.getPimage()%>" alt="Card image cap">
         <%
        }
       
        	
         %>
         
          <div class="card-body">
           
                <b><%= post.getPtitle()%></b>
                <p style="  height:60px; line-height:20px;overflow:hidden;"id="paraContent<%= post.getPid()%>"><%= post.getPcontent()%>
                <p style="display:inline;"  id="readDots<%= post.getPid()%>" >.......</p>
                   <button onclick="showContent(<%= post.getPid()%>)" id="readButton<%= post.getPid()%>" >Read More</button>
             
                </p>
               

            </div>
            
            <div class="card-footer primary-background ">
             
                 <!-- Upvote -->
              
                <button id="upVoteButton<%= post.getPid()%>" style="padding: 5px 14px;background-color: #e7e7e7;cursor: pointer;" class="upvote-background" 
                onclick="increaseUpVote(<%= post.getPid()%>)">
                  </button>
                 
                 <p style="display:inline" id="voteCount<%= post.getPid()%>"><%=post.getVotecount() %></p>
            
                 <!-- Downvote -->
                  <button 
                  id="downVoteButton<%= post.getPid()%>" 
                  style="padding: 5px 14px;background-color:#e7e7e7;cursor: pointer;"
                   class="downvote-background" 
                   onclick="increaseDownVote(<%= post.getPid()%>)" 
                   >
                  </button>
                   <script>
                	 userLoggedIn=1;
                	</script>
                     <%
              
                 if(user==null)
                	{
                	 %>
                	 <script>
                	 userLoggedIn=0;
                	  
                	 </script>
                	 <%
                	}
                 else
                 {
                	 int getVote=postd.isVoted(user.getId(),post.getPid());
                	 %>
                	 <script>
                	 pid=<%=post.getPid()%>;
                	 </script>
                	 <% 
                	 if(getVote==1)
                			 {
                		 %>
                		 <script>
                        	document.getElementById("upVoteButton"+pid).style.backgroundColor = "#4CAF50";//Green
                      		document.getElementById("upVoteButton"+pid).disabled = true;
                      		document.getElementById("upVoteButton"+pid).style.cursor ="default";
                      		 </script>
                     	<%
                			 }
                	 
                	 else if(getVote==-1)
        			 {
        		 %>
                     		 <script>
                		 
                      		document.getElementById("downVoteButton"+pid).style.backgroundColor = "#f44336"; //red
                      		document.getElementById("downVoteButton"+pid).disabled = true;
                      		document.getElementById("downVoteButton"+pid).style.cursor ="default";
                        </script>
                		 <%
                			 }
                 }
                	 %>
                	 
                <a style="  margin-left:200px;display:inline;" href="#!" class="btn btn-outline-light btn-sm"> <i class="fa fa-commenting-o"></i> <span>20</span>  </a>
            </div>
           
        </div>


    </div>


    <%
        }
        
    %>

</div>

             </div>  

        </div>
                        </div>
                    </div>
                    </div>


                </div>

           

        </div>


                        </div>
                    </div>


            </div>

  

        <!--end of main content  of body-->
</div>        
        <!--javascripts-->
        <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
                                $(document).ready(function () {
                             
                                	let editStatus = false;
                                    $('#edit-profile-button').click(function ()
                                    {
                                        if (editStatus == false)
                                        {
                                            $("#profile-details").hide()
                                            $("#profile-edit").show();
                                            editStatus = true;
                                            $(this).text("Back")
                                        } else
                                        {
                                            $("#profile-details").show()
                                            $("#profile-edit").hide();
                                            editStatus = false;
                                            $(this).text("Edit")
                                        }
                                    })
                                });
             </script>
          

<script>
function showContent( id) {
	  var dots = document.getElementById("readDots"+id);
  var btnText = document.getElementById("readButton"+id);
  var moreText = document.getElementById("paraContent"+id);

  if (dots.style.display === "none") {
    dots.style.display = "inline";
    btnText.innerHTML = "Read more"; 
    moreText.style.height="60px";
    moreText.style.overflow="hidden";
  } else {
    dots.style.display = "none";
    btnText.innerHTML = "Read less"; 
    moreText.style.height="auto";
    moreText.style.overflow="visible";
  }
}
</script>

<!-- For Voting System -->

           <script>
 
           function increaseUpVote(id)
            {
        	   if(userLoggedIn==0)
            	{
            	alert("Plz Login to vote");
            	return;
            	}
            document.getElementById("upVoteButton"+id).style.backgroundColor = "#4CAF50";//Green
            document.getElementById("upVoteButton"+id).disabled = true;
            document.getElementById("downVoteButton"+id).disabled =false;
            document.getElementById("downVoteButton"+id).style.backgroundColor = "#e7e7e7"; //gray
            document.getElementById("upVoteButton"+id).style.cursor ="default";
            document.getElementById("downVoteButton"+id).style.cursor ="pointer";
        	
            req=new XMLHttpRequest();
        	   req.open("GET","Voting?action=upvote&id="+id,true);
        	   req.send();
        	   document.getElementById("downVoteButton"+id).style.cursor ="pointer";
        	   var x=parseInt(document.getElementById("voteCount"+id).innerHTML)+1;
        	   document.getElementById("voteCount"+id).innerHTML= x;
               
           }
           function increaseDownVote(id)
           {
        	   if(userLoggedIn==0)
       	     {
           	alert("Plz Login to vote");
           	return;
           	}
        	   document.getElementById("upVoteButton"+id).disabled =false;
        	   document.getElementById("downVoteButton"+id).style.backgroundColor = "#f44336"; //red
        	   document.getElementById("downVoteButton"+id).disabled = true;
        	   document.getElementById("downVoteButton"+id).style.cursor ="default";
        	   document.getElementById("upVoteButton"+id).style.cursor ="pointer"; 
        	   document.getElementById("upVoteButton"+id).style.backgroundColor = "#e7e7e7"; //gray
        	   req=new XMLHttpRequest();
        	   req.open("GET","Voting?action=downvote&id="+id,true);  
        	   req.send();
        	   var x=parseInt(document.getElementById("voteCount"+id).innerHTML)-1;
        	   document.getElementById("voteCount"+id).innerHTML= x;
               	   
           }
           </script>
</body>
</html>	