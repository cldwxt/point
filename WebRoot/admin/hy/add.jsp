<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page import="com.bean.*" %>
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sn.getDir();
%>
<HTML><HEAD><TITLE>后台操作区</TITLE>
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet>
<SCRIPT language=JavaScript src="<%=basePath %><%=dir %>/images/Common.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=basePath %>images/calendar.js"></SCRIPT>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%>
<script type="text/javascript">
function checkww22()
{//String xm="";String xb="";String cs="";String fm="";String zz="";String dh="";String yx=""; 
	if(document.form1.xm.value==""||document.form1.cs.value==""||document.form1.fm.value==""||document.form1.zz.value==""||
	document.form1.dh.value==""||document.form1.yx.value=="" )
	{
		alert("所有项目必须填写！");
		return false;
	}
	 
	 
}
</script>
<%
	String username=(String)session.getAttribute("user");
	if(username==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
	String xm="";String xb="";String cs="";String fm="";String zz="";String dh="";String yx=""; 
	String id="";
	String method=request.getParameter("method");
	if(method.equals("uphy")){
		id=request.getParameter("id");	
		List list2=cb.get1Com("select * from hy where id='"+id+"'",8);
		xm=list2.get(1).toString();xb=list2.get(2).toString();cs=list2.get(3).toString();
		fm=list2.get(4).toString();zz=list2.get(5).toString();dh=list2.get(6).toString();yx=list2.get(7).toString(); 
	}
%>
<BODY >
	   <form action="<%=basePath %>ComServlet?method=<%=method%>&id=<%=id%>" name="form1" method="post" onsubmit="return checkww22()"   >
	<TABLE width="100%" border=0 align="center" cellPadding=3 cellSpacing=1 class=tablewidth>
		  <TBODY>
		  <TR align="center">  
		  <TD width=40% align=right>会员姓名： </TD> 
		  <TD align=left><input type=text name=xm  size=30 maxlength=30 value=<%=xm %>> </TD>
		  </TR>
		  <TR align="center">  
		  <TD width=40% align=right>会员性别： </TD>
		  <TD align=left><INPUT type="radio" name="xb" value="男" checked="checked"> 男 <input type="radio" name="xb" value="女" >女</TD>
		  </TR>
		  <TR align="center">  
		  <TD width=40% align=right>身份证号码： </TD>
		  <TD align=left><input type=text name=cs  size=30 maxlength=30 value=<%=cs %>> </TD>
		  </TR>
		  <TR align="center">  
		  <TD width=40% align=right>会员生日： </TD>
		  <TD align=left><input type=text name=fm  size=30 maxlength=30 value=<%=fm %>> 请注意：时间格式：00-00 </TD>
		  </TR>
		  <TR align="center">  
		  <TD width=40% align=right>家庭住址： </TD>
		  <TD align=left><input type=text name=zz size=30 maxlength=30 value=<%=zz %>> </TD>
		  </TR>
		  <TR align="center">  
		  <TD width=40% align=right>联系电话： </TD>
		  <TD align=left><input type=text name=dh size=30 maxlength=30 value=<%=dh %>>   </TD>
		  </TR>
		  <TR align="center">  
		  <TD width=40% align=right>备注： </TD>
		  <TD align=left><input type=text name=yx size=30 maxlength=30 value=<%=yx %>> </TD>
		  </TR> 
		  <TR align="center">  
		  <TD  colspan=2 align=center><input type=submit  value=提交> </TD>
		  </TR>
		  </TBODY>
		  </TABLE>
		  </form>
</BODY>
<%} %>

</HTML>
