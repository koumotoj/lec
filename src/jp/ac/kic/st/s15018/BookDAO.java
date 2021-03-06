package jp.ac.kic.st.s15018;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {

	public BookListBean findBook(String name, String author, String isbn, int publishYear, boolean isSelectPublish){
		Connection conn = null;
		try{
			conn = DBManager.getConnection();
			BookListBean bookListBean = new BookListBean();
			String sql = "SELECT * FROM book WHERE book_name LIKE ? AND author LIKE ? AND isbn LIKE ? ";
			if(isSelectPublish){
				sql += " AND publish_year = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+ name +"%");
			pstmt.setString(2, "%"+ author +"%");
			pstmt.setString(3, "%" + isbn + "%");
			if(isSelectPublish){
				pstmt.setInt(4, publishYear);
			}
			//	System.out.println(sql);
			ResultSet rs = null;
			try{
				rs = pstmt.executeQuery();
			}catch(Exception e){
				System.err.println(e);
				System.err.println("SQL構文エラー ");
			}
			while(rs.next()){
				 String getName = rs.getString("book_name");
				 String getAuthor = rs.getString("author");
				 String getIsbn = rs.getString("isbn");
				 int getPublishYear = rs.getInt("publish_year");
				 boolean getKashidashi = rs.getBoolean("kashidashi");
				 BookBean bookBean = new BookBean(getName, getAuthor, getIsbn, getPublishYear, getKashidashi);
				 bookListBean.add(bookBean);
			}
			return bookListBean;

		}catch (Exception e){
			System.err.println(e);
			return null;
		}
	}

	public int add(BookBean bean){
		String name = bean.getName();
		String author = bean.getAuthor();
		String isbn = bean.getIsbn();
		int publishYear = bean.getPublishYear();
		boolean kashidashi = bean.getKashidashi();
		int result = 0;

		Connection conn = null;
		try{
			conn = DBManager.getConnection();
			String sql = "INSERT INTO book (book_name, author, isbn, publish_year, kashidashi) " +
							"VALUES (?, ?, ?, ?, ?);";
			//System.err.println(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, author);
			pstmt.setString(3, isbn);
			pstmt.setInt(4, publishYear);
			pstmt.setBoolean(5, kashidashi);
			try{
				result = pstmt.executeUpdate();
				return result;
			}catch(Exception e){
				System.err.println(e);
				System.err.println("SQL構文エラー ");
			}
		}catch (Exception e){
			System.err.println(e);
		}
		return result;

	}

	public BookBean findById(int id){
		Connection conn = null;
		try{
			conn = DBManager.getConnection();
			BookBean bean = new BookBean();
			String sql = "SELECT * FROM book WHERE book_id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = null;
			try{
				rs = pstmt.executeQuery();
			}catch(Exception e){
				System.err.println(e);
				System.err.println("SQL構文エラー ");
			}
			rs.next();
			String getName = rs.getString("book_name");
			String getAuthor = rs.getString("author");
			String getIsbn = rs.getString("isbn");
			int getPublishYear = rs.getInt("publish_year");
			boolean getKashidashi = rs.getBoolean("kashidashi");
			bean = new BookBean(getName, getAuthor, getIsbn, getPublishYear, getKashidashi);
			return bean;

		}catch (Exception e){
			System.err.println(e);
		}
		return null;
	}


}