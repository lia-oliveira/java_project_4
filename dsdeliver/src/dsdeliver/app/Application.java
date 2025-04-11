package dsdeliver.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import dsdeliver.app.entities.Order;
import dsdeliver.app.entities.OrderStatus;
import dsdeliver.app.entities.Product;

public class Application {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
		
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("select * from tb_order");
		
		while (rs.next()) {	
			Order order = instantiateOrder(rs);			
			System.out.println(order);
		}
	}
	
	private static Product instantiateProduct(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getLong("id"));
		p.setDescription(rs.getString("description"));
		p.setName(rs.getString("name"));
		p.setImgUrl(rs.getString("image_uri"));
		p.setPrice(rs.getDouble("price"));
		return p;
	}
	private static Order instantiateOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setLatitude(rs.getDouble("latitude"));
		order.setLongitude(rs.getDouble("longitude"));
		order.setMoment(rs.getTimestamp("moment").toInstant());
		order.setStatus(OrderStatus.values()[rs.getInt("status")]);
		return order;
	}

}
