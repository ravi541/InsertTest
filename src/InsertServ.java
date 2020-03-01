

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class InsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  ServletConfig sc;
    public InsertServ() {
        super();
        
        
    }
    public void init(ServletConfig sc)
    {
    this.sc=sc;	
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		int eno=Integer.parseInt(req.getParameter("eno"));
		String ename=req.getParameter("ename");
		double sal=Double.parseDouble(req.getParameter("sal"));
		
		String driver=sc.getInitParameter("driver");
		String url=sc.getInitParameter("url");
		String user=sc.getInitParameter("user");
		String pwd=sc.getInitParameter("pwd");
		try
		{
		Class.forName(driver);
			
		Connection con=DriverManager.getConnection(url,user,pwd);
		PreparedStatement pst=con.prepareStatement("Insert into emp values(?,?,?)");
		
		
		pst.setInt(1, eno);
		pst.setString(2,ename);
		pst.setDouble(3,sal);
		int result=pst.executeUpdate();
		pw.println("<h2>"+result+" records inserted </h2>"); 
	/*	Statement pst=con.createStatement();
		ResultSet rs=pst.executeQuery("Select * from emp where empno="+eno);
		if(rs.next())
	     {
	    	pw.println("-------------------------------");
	    	 pw.println("Empno\tEname\tsal");
	    	 pw.println("---------------------<br>----------");
	    	 pw.println("-------------------------------");
	    	 pw.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
	     }
	     else
	     {
	    	 pw.println("No record found  ");
	     }*/
		}
		
		catch(SQLException se)
		{
			pw.println("Unable to insert record");
		}
		catch(Exception e)
		{
			pw.println(e);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		
		doGet(req, res);
	}

}
