
      1 <%
      2   // Read previous comments from session
      3   String comments = (String)session.getAttribute("comments");
      4   if(null == comments){
      5     comments = "";
      6   }
      7
      8   // If this is a form submission, add the new comment
      9   // to the existing ones
     10   String comment  = request.getParameter("comment");
     11   if(null != comment){
     12     comments = comments + comment + "<br />";
     13     session.setAttribute("comments", comments);
     14     response.sendRedirect(request.getContextPath() + "/prg.jsp");
     15     return;
     16   }
     17 %>
     18 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
     19 <html>
     20   <head>
     21     <title>Simple Post Redirect Get Pattern</title>
     22     <meta http-equiv="Expires"       content="Sat, 01 Dec 2001 00:00:00 GMT" />
     23     <meta http-equiv="pragma"        content="no-cache" />
     24     <meta http-equiv="Cache-Control" content="no-cache" />
     25   </head>
     26   <body>
     27     <fieldset>
     28       <legend>Previous Comments</legend>
     29       <%=comments%>
     30     </fieldset>
     31     <form method="post" action="<%=request.getContextPath()%>/prg.jsp">
     32       <fieldset>
     33         <legend>Add a new comment</legend>
     34         <input type="text" name="comment" id="comment" />
     35         <input type="submit" name="enter_button" id="Enter" />
     36       </fieldset>
     37     </form>
     38   </body>
     39 </html>