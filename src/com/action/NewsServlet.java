package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.NewsBean;
import com.bean.SystemBean;
import com.util.Constant;
import com.util.Filter;

public class NewsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NewsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		String sysdir = new SystemBean().getDir();
		HttpSession session = request.getSession();
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		try{
			String username2 = (String)session.getAttribute("user");
			 
				String method = request.getParameter("method").trim();
				if(method.equals("addNews")){
					String title = request.getParameter("title");
					String fenlei = request.getParameter("fenlei");
					String infoContent = request.getParameter("infoContent");
					int flag = new NewsBean().exeUp("insert into news(title,fenlei,content,addtime,adder,visit) " +
							"values('"+title+"','"+fenlei+"','"+infoContent+"','"+date+"','"+username2+"','0')");
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("upNews")){
					String id = request.getParameter("id");
					String title = request.getParameter("title");
					String fenlei = request.getParameter("fenlei");
					String infoContent = request.getParameter("infoContent");
					int flag = new NewsBean().exeUp("update news set title='"+title+"',fenlei='"+fenlei+"',content='"+infoContent+"' where id='"+id+"'");
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("delNews")){
					String id = request.getParameter("id");
					int flag = new NewsBean().exeUp("delete from news  where id='"+id+"'");
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher(sysdir+"/news/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("addPrep")){
					String member=(String)session.getAttribute("member");
					String sid = request.getParameter("sid");
					String title = request.getParameter("title");
					String rs = request.getParameter("rs");
					String sj = request.getParameter("sj");
					String ts = request.getParameter("ts");
					String lxr = request.getParameter("lxr");
					String lxfs = request.getParameter("lxfs");
					new NewsBean().exeUp("update sale set dj=dj+1 where id='"+sid+"'");
					int flag = new NewsBean().exeUp("insert into prep(title,rs,sj,ts,lxr,lxfs,addtime,member,zt,ddid) " +
							"values('"+title+"','"+rs+"','"+sj+"','"+ts+"','"+lxr+"','"+lxfs+"','"+date+"','"+member+"','未提交','0')");
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "预订成功，稍后本站客服人员会与您取得联系。您可以在会员中心查看您的预订记录！");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}
				else if(method.equals("upPrep")){
					String member=(String)session.getAttribute("member");
					String id = request.getParameter("id"); 
					String rs = request.getParameter("rs");
					String sj = request.getParameter("sj");
					String ts = request.getParameter("ts");
					String lxr = request.getParameter("lxr");
					String lxfs = request.getParameter("lxfs");
					int flag = new NewsBean().exeUp("update prep set rs='"+rs+"',sj='"+sj+"',ts='"+ts+"',lxr='"+lxr+"'," +
							"lxfs='"+lxfs+"' where id='"+id+"'");
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("delPrep")){
					String member=(String)session.getAttribute("member");
					String id = request.getParameter("id");
					int flag = new NewsBean().exeUp("delete from prep where id='"+id+"'");
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
					}
				}
				 
				
				else if(method.equals("addDD")){
					String member=(String)session.getAttribute("member");
					String fkfs = request.getParameter("fkfs");
					String ddid=String.valueOf(System.currentTimeMillis());
					String sql="update prep set zt='已提交',ddid='"+ddid+"' where member='"+member+"' and ddid='0'";
					//System.out.println(sql);
					NewsBean nb=new NewsBean();nb.exeUp(sql);
					int flag = nb.exeUp("insert into dd(ddid,member,zt,fkfs,addtime) values('"+ddid+"','"+member+"','未发货','"+fkfs+"','"+date+"')");
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
					}
				}
				
				else if(method.equals("fDD")){
					String ddid = request.getParameter("ddid");
					NewsBean nb=new NewsBean(); 
					int flag = nb.exeUp("update dd set zt='已发货' where ddid='"+ddid+"'");
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
					}
				}
				else if(method.equals("dDD")){
					String ddid = request.getParameter("ddid");
					NewsBean nb=new NewsBean(); 
					int flag = nb.exeUp("delete from dd where ddid='"+ddid+"'");
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
					}
				}
				else if(method.equals("dDD2")){
					String ddid = request.getParameter("ddid");
					NewsBean nb=new NewsBean(); 
					int flag = nb.exeUp("delete from dd where ddid='"+ddid+"'");
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("admin/prep/fh.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("admin/prep/fh.jsp").forward(request, response);
					}
				}
				////////////////////////////////////////////////////////////////////////////////查询统计
				else if(method.equals("sDDrq")){
					String year=request.getParameter("year");
					 String month=request.getParameter("month");
					 String day=request.getParameter("day");
					 String str=year+"-"+month+"-"+day;
					 String sql="select * from dd where addtime like '%"+str+"%'  ";
					 request.setAttribute("sql", sql);
					 request.getRequestDispatcher("admin/prep/s1.jsp").forward(request, response);
				}
				else if(method.equals("sDDid")){
					String ddid=request.getParameter("ddid"); 
					 request.setAttribute("ddid", ddid);
					 request.getRequestDispatcher("admin/prep/s2.jsp").forward(request, response);
				}
				else if(method.equals("sYye")){
					String year=request.getParameter("year");
					 String month=request.getParameter("month");
					 String day=request.getParameter("day");
					 
					 String year2=request.getParameter("year2");
					 String month2=request.getParameter("month2");
					 String day2=request.getParameter("day2");
					 String str=year+"-"+month+"-"+day+" "+"00:00:00";
					 String str2=year2+"-"+month2+"-"+day2+" "+"23:59:59";
					 String sql="select  * from  prep where  addtime>'"+str+"' and addtime<'"+str2+"' order by  id desc";
					 //String sql="select * from prep where addtime>'"+str+"' and addtime<'"+str2+"'  ";
					 request.setAttribute("sql", sql);
					 request.getRequestDispatcher("admin/prep/s3.jsp").forward(request, response);
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
