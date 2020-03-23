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
	if(document.form1.kh.value==""||document.form1.xm.value==""||document.form1.rq.value==""||document.form1.je.value=="" )
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
	String kh="";String xm="";String rq="";String je=""; 
	String id="";
	String method=request.getParameter("method");
	if(method.equals("uphyk")){
		id=request.getParameter("id");	
		List list2=cb.get1Com("select * from hyk where id='"+id+"'",5);
		kh=list2.get(1).toString();xm=list2.get(2).toString();rq=list2.get(3).toString();je=list2.get(4).toString(); 
	}
%>
<BODY >
	   <form action="<%=basePath %>ComServlet?method=<%=method%>&id=<%=id%>" name="form1" method="post" onsubmit="return checkww22()"   >
	<TABLE width="100%" border=0 align="center" cellPadding=3 cellSpacing=1 class=tablewidth>
		  <TBODY>
		  <TR align="center">  
		  <TD width=40% align=right>会员卡号码： </TD> 
		  <TD align=left><input type=text name=kh  size=30 maxlength=30 value=<%=kh %>> </TD>
		  </TR>
		  <TR align="center">  
		  <TD width=40% align=right>会员姓名： </TD>
		  <TD align=left><select name=xm>
    <%List cl=cb.getCom("select * from hy order by id desc",3);if(!cl.isEmpty()){for(int i=0;i<cl.size();i++){List list2=(List)cl.get(i);%>
    <option value="<%=list2.get(0).toString() %>"><%=list2.get(1).toString() %></option>
    <%}} %>
    </select></TD>
		  </TR>
		  <TR align="center">  
		  <TD width=40% align=right>开卡日期： </TD>
		  <TD align=left><input type=text name=rq  size=30 maxlength=30 value=<%=rq %>> 请注意：时间格式：0000-00-00</TD>
		  </TR>
		  <TR align="center">  
		  <TD width=40% align=right>充值金额： </TD>
		  <TD align=left><input type=text name=je  size=30 maxlength=30 value="<%=je %>" onkeyup="if(!(/^[\d]+\.?\d*$/.test(this.value))){alert('您的输入有误');this.value='';this.focus();}"> 元</TD>
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
