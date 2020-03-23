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
{//String mc="";String js="";String jg="";String jf="";String bj="";  
	if(document.form1.mc.value==""||document.form1.jf.value=="" )
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
	String mc="";String jf=""; 
	String id="";
	String method=request.getParameter("method");
	if(method.equals("updhsp")){
		id=request.getParameter("id");	
		List list2=cb.get1Com("select * from dhsp where id='"+id+"'",3);
		mc=list2.get(1).toString();jf=list2.get(2).toString(); 
	}
%>
<BODY >
	   <form action="<%=basePath %>ComServlet?method=<%=method%>&id=<%=id%>" name="form1" method="post" onsubmit="return checkww22()"   >
	<TABLE width="100%" border=0 align="center" cellPadding=3 cellSpacing=1 class=tablewidth>
		  <TBODY>
		  <TR align="center">  
		  <TD width=40% align=right>&nbsp;商品名称： </TD> 
		  <TD align=left><input type=text name=mc  size=30 maxlength=30 value=<%=mc %>> </TD>
		  </TR> 
		  <TR align="center">  
		  <TD width=40% align=right>兑换积分： </TD>
		  <TD align=left><input type=text name=jf  size=30 maxlength=30 value="<%=jf %>" onkeyup="if(!(/^[\d]+\.?\d*$/.test(this.value))){alert('您的输入有误');this.value='';this.focus();}">  </TD>
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
