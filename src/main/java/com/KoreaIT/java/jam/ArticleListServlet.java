package com.KoreaIT.java.jam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		// DB 연결
		String url = "jdbc:mysql://127.0.0.1:3306/JSPAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		String user = "root";
		String password = "";
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("예외 : 클래스가 없습니다");
			System.out.println("프로그램을 종료합니다");
			return;
		}

		try {
			conn = DriverManager.getConnection(url, user, password);

			response.getWriter().append("Success!!!");
			
			String inputedPage = request.getParameter("page");
			
			if(inputedPage == null) {
				inputedPage = "1";
			}
			
			int page = Integer.parseInt(inputedPage);
			
			int itemsInApage = 10;
			int limitFrom = (page - 1) * itemsInApage;
			
			
			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("ORDER BY id DESC");
			sql.append("LIMIT ?, ?", limitFrom, itemsInApage);
			List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
			
			
			response.getWriter().append(articleRows.toString());

			
			SecSql sql2 =  SecSql.from("SELECT count(*)");
			sql2.append("FROM article");
			
			int pagenum = DBUtil.selectRowIntValue(conn, sql2);
			
			request.setAttribute("pagenum", pagenum);
			request.setAttribute("articleRows", articleRows);
			
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
			
			

			
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}